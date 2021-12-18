package com.tao.service;

import com.tao.pojo.vo.WordList;

import java.io.IOException;
import java.util.Map;

/**
 * @InterfaceName ThymeleafService
 * @Author HETAO
 * @Date 2021/3/21 11:05
 */
public interface ThymeleafService {
    void createHtml(Map params) throws IOException;

    void deleteHtml();
}
