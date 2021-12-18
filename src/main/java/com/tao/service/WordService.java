package com.tao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.entity.Word;

import java.io.IOException;
import java.util.List;

/**
 * (Word)表服务接口
 *
 * @author hetao
 * @since 2021-03-19 16:02:30
 */
public interface WordService extends IService<Word> {

    void batchInsertWord(List<String> words, Integer wordTypeId,String roots_and_affixes) throws IOException;
}
