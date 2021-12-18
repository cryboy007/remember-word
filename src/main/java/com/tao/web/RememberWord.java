package com.tao.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.tao.entity.Word;
import com.tao.entity.WordType;
import com.tao.pojo.vo.WordList;
import com.tao.service.ThymeleafService;
import com.tao.service.WordService;
import com.tao.service.WordTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName RememberWord
 * @Author HETAO
 * @Date 2021/3/20 19:33
 */
@Controller
@Slf4j
public class RememberWord {
    @Resource
    private WordTypeService wordTypeService;

    @Resource
    private WordService wordService;

    @Resource
    private ThymeleafService thymeleafService;

    @GetMapping("page/rememberWord")
    public String rememberWord(Model model) {
        log.debug("page/rememberWord");
        /**
         * 1.查找出所有词缀.词根.以及单词
         * 2.未来使用静态化方式
         */
        List<WordList> wordLists = wordTypeService.loadContent();
        model.addAttribute("wordTypes",wordLists);
        return "rememberWord";
    }

    @GetMapping("initRememberWord")
    @ResponseBody
    public R initHtml() throws IOException {
        thymeleafService.createHtml(null);
        return R.ok(null);
    }
}
