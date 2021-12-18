package com.tao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tao.entity.List;
import com.tao.entity.NacosInstance;
import com.tao.util.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NacosTest
 * @Author tao.he
 * @Since 2021/8/19 14:04
 */
@Slf4j
public class NacosTest {


    public static void main(String[] args) throws JsonProcessingException {
        String serviceName = "e3plus-app-integration";
        String port = "18067";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuYWNvcyIsImF1dGgiOiIiLCJleHAiOjE2MjkzNzAwNTR9.l_XdYJch-mbdxzVzo9QoAWfvePBRsAfFLHPnke5LGTE");
        Map<String, Object>  map = new HashMap<>();
        while (true) {
            String url = String.format("http://192.168.145.164:32006/nacos/v1/ns/catalog/instances?serviceName=%s&clusterName=DEFAULT" +
                    "&groupName=DEFAULT_GROUP&pageSize=10&pageNo=1&namespaceId=", serviceName);
            JSONObject nacosInstance = RestTemplateUtil.get(url,
                    map, new TypeReference<JSONObject>() {
                    });
            Integer count = nacosInstance.getInteger("count");
            JSONArray list = nacosInstance.getJSONArray("list");
            if (count != 0 ) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    JSONObject jsonObject = list.getJSONObject(i);
                    String ip = jsonObject.getString("ip");
                    Boolean enabled = jsonObject.getBoolean("enabled");
                    if (!ip.startsWith("10.42.") && enabled) {
                        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();  //表单数据结构
                        param.add("serviceName", serviceName);//e3plus-app-integration
                        param.add("clusterName", "DEFAULT");
                        param.add("port", port);
                        param.add("ephemeral", "true");
                        param.add("weight", "1");
                        param.add("metadata","{\"preserved.register.source\":\"SPRING_CLOUD\"}");
                        param.add("namespaceId","");
                        //开始下线
                        param.add("ip", ip);
                        param.add("enabled","false");
                        String result = RestTemplateUtil.post("http://192.168.145.164:32006/nacos/v1/ns/instance", param,httpHeaders,
                                new TypeReference<String>() {
                        });
                        log.debug("ip===>{}》{}", ip,"已下线");
                    }
                }
            }
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
