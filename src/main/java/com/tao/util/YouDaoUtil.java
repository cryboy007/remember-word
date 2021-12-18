package com.tao.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tao.dao.WordDao;
import com.tao.dao.WordTypeDao;
import com.tao.entity.Word;
import com.tao.entity.WordType;
import com.tao.pojo.YouDaoYun;
import com.tao.service.WordService;
import com.tao.service.WordTypeService;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName YouDaoUtil
 * @Author HETAO
 * @Date 2021/3/19 16:24
 */
@ConfigurationProperties(prefix = "youdao")
public class YouDaoUtil {

        private static final ObjectMapper objectMapper = new ObjectMapper();

        private static Logger logger = LoggerFactory.getLogger(YouDaoYun.class);

        @Value("${youdao.YOUDAO_URL}")
        private String YOUDAO_URL;
        @Value("${youdao.APP_KEY}")
        private String APP_KEY;
        @Value("${youdao.APP_SECRET}")
        private String APP_SECRET;

        @Resource
        private WordTypeDao wordTypeDao;

        @Resource
        private WordDao wordDao;

        public void createWord(String q,Integer wordType,String rootsAndAffixes) throws IOException {
        List<Word> list = wordDao.selectList(new QueryWrapper<Word>().eq("learning_word",q));
        Map<String,String> params = new HashMap<String,String>();
        if (CollectionUtils.isNotEmpty(list)) {
            logger.debug("%s_已经存在",q);
            return;
        }
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("from", "zh-CHS");
        params.put("to", "en");
        params.put("signType", "v3");
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr);
        params.put("appKey", APP_KEY);
        params.put("q", q);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("roots_and_affixes",rootsAndAffixes);
        //params.put("vocabId","您的用户词表ID");
        /** 处理结果 */
        requestForHttp(YOUDAO_URL,params,wordType);
    }

        public String requestForHttp(String url, Map<String,String> params,Integer  wordType) throws IOException {

        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        if (CollectionUtils.isNotEmpty(params) && params.containsKey("q")) {
            Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String,String> en = it.next();
                String key = en.getKey();
                String value = en.getValue();
                if (key.equals("roots_and_affixes"))
                    continue;
                paramsList.add(new BasicNameValuePair(key,value));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            logger.info("Content-Type:" + contentType[0].getValue());
            if("audio/mp3".equals(contentType[0].getValue())){
                //如果响应是wav
                HttpEntity httpEntity = httpResponse.getEntity();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(baos);
                byte[] result = baos.toByteArray();
                EntityUtils.consume(httpEntity);
                if(result != null){//合成成功
                    String q = params.get("word");
                  /*  String time = DateTimeUtil.getLocalDate(LocalDate.now(), "yyyy/MM/dd");
                    FileUtils.forceMkdir(new File(time));
                    String file = time + "/" +q + ".mp3";*/

                    String affixes = params.get("roots_and_affixes");
                    String path = "audio" +"/"+ affixes;
                    FileUtils.forceMkdir(new File(path));
                    String file = path + "/" +q + ".mp3";
                    File wordFile = new File(file);
                    if (!wordFile.exists()) {
                        byte2File(result,file);
                    }
                    return file;
                }
            }else{
                /** 响应不是音频流，直接显示结果 */
                HttpEntity httpEntity = httpResponse.getEntity();
                String json = EntityUtils.toString(httpEntity,"UTF-8");
                EntityUtils.consume(httpEntity);
                String jsonString = json.replaceAll("-", "_");
                YouDaoYun youDaoYun = objectMapper.readValue(jsonString, YouDaoYun.class);
                Word word = new Word();
                //单词
                word.setLearningWord(youDaoYun.getQuery());
                //词义
                if (youDaoYun.getBasic() == null) {
                    return null;
                }
                word.setExplaination(youDaoYun.getBasic().getExplains().toString());
                //播放
                String tSpeakUrl = youDaoYun.getTSpeakUrl();
                String filePath = null;
                if (StringUtils.isNotBlank(tSpeakUrl)) {
                    Map map = new HashMap(1);
                    map.put("word",params.get("q"));
                    map.put("roots_and_affixes",params.get("roots_and_affixes"));
                    //下载音频
                    filePath = this.requestForHttp(tSpeakUrl, map,null);
                }
                word.setPronunciation(filePath);
                //例句
                word.setSentence(null);
                //音标
                word.setPhonogram(youDaoYun.getBasic().getUs_phonetic());
                //词性
                word.setNature(null);
                //例句翻译
                word.setSentenceExp(null);
                //添加日期
                word.setAddTime(new Date());
                word.setUpdateTime(new Date());
                word.setWordType(wordType);
                wordDao.insert(word);
                WordType type = new WordType();
                Integer amount = wordTypeDao.selectCount(null);
                type.setAmount(++amount);
                type.setId(wordType);
                wordTypeDao.updateWordTypeCountById(type);
            }
        }finally {
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                logger.info("## release resouce error ##" + e);
            }
        }
            return null;
        }

        /**
         * 生成加密字段
         */
        public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

        /**
         *
         * @param result 音频字节流
         * @param file 存储路径
         */
        private static void byte2File(byte[] result, String file) {
        File audioFile = new File(file);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(audioFile);
            fos.write(result);

        }catch (Exception e){
            logger.info(e.toString());
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

        public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }
}
