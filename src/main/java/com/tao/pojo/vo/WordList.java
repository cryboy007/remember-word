package com.tao.pojo.vo;

import com.tao.entity.Word;
import com.tao.entity.WordType;
import lombok.Data;

import java.util.List;

/**
 * @ClassName WordList
 * @Author HETAO
 * @Date 2021/3/20 21:51
 */
@Data
public class WordList {
    private WordType wordType;
    private List<Word> words;
}
