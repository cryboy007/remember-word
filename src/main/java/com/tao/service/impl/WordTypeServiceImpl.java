package com.tao.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.dao.WordDao;
import com.tao.dao.WordTypeDao;
import com.tao.entity.Word;
import com.tao.entity.WordType;
import com.tao.pojo.vo.WordList;
import com.tao.service.WordTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 词根,词缀表(WordType)表服务实现类
 *
 * @author hetao
 * @since 2021-03-19 16:02:36
 */
@Service("wordTypeService")
public class WordTypeServiceImpl extends ServiceImpl<WordTypeDao, WordType> implements WordTypeService {

    @Autowired
    private WordTypeDao wordTypeDao;

    @Autowired
    private WordDao wordDao;

    public List<WordList> loadContent() {
        List<WordType> list = wordTypeDao.selectList(new QueryWrapper<WordType>().orderByAsc("sort"));
        List<Word> words = wordDao.selectList(null);
        List<WordList> wordLists = new ArrayList<>();
        for (WordType wordType : list) {
            WordList wordList = new WordList();
            wordList.setWordType(wordType);
            ArrayList<Word> wordArrayList = new ArrayList<>();
            for (Word word : words) {
                if (word.getWordType() == wordType.getId())
                    wordArrayList.add(word);
            }
            wordList.setWords(wordArrayList);
            wordLists.add(wordList);
        }
        return wordLists;
    }
}
