package com.tao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tao.dao.WordDao;
import com.tao.dao.WordTypeDao;
import com.tao.entity.Word;
import com.tao.entity.WordType;
import com.tao.pojo.vo.WordList;
import com.tao.service.ThymeleafService;
import com.tao.service.WordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ThymeleafServiceImpl
 * @Author HETAO
 * @Date 2021/3/21 11:06
 */
@Service
@Slf4j
public class ThymeleafServiceImpl implements ThymeleafService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private WordTypeService wordTypeService;


    @Override
    public void createHtml(Map params) throws IOException {
        String rootPath = System.getProperty("user.dir");
        List<WordList> wordLists = wordTypeService.loadContent();
        // 上下文
        Context context = new Context();
        Map map = new HashMap(1);
        map.put("wordTypes",wordLists);
        context.setVariables(map);

        // 输出流
        String path = rootPath + "/static/page";
        FileUtils.forceMkdir(new File(path));
        File dest = new File(path,   "rememberWord.html");
        if (dest.exists()) {
            dest.delete();
        }
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            // 生成html，第一个参数是thymeleaf页面下的原型名称
            templateEngine.process("rememberWord", context, writer);
        } catch (Exception e) {
            log.error("[静态页服务]：生成静态页异常", e);
        }
    }

    @Override
    public void deleteHtml() {

    }
}
