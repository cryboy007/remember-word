package com.tao.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.dao.WordDao;
import com.tao.entity.Word;
import com.tao.entity.WordType;
import com.tao.service.WordService;
import com.tao.service.WordTypeService;
import com.tao.util.LambdaExceptionUtil;
import com.tao.util.YouDaoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * (Word)表服务实现类
 *
 * @author hetao
 * @since 2021-03-19 16:02:30
 */
@Service("wordService")
public class WordServiceImpl extends ServiceImpl<WordDao, Word> implements WordService {
    @Resource
    private YouDaoUtil youDaoUtil;

    @Resource
    private WordTypeService wordTypeService;
    /**
     * 根据传入的单词,查询后入库
     * @param words
     * @param wordTypeId
     */
    @Override
    public void batchInsertWord(List<String> words, Integer wordTypeId,String roots_and_affixes) throws IOException {
/*      不在此处做录入功能
        WordType wordType = new WordType();
        wordType.setWord(roots_and_affixes);
        wordType.setWordType();
        wordType.setSort();
        wordType.setCreateTime();
        wordType.setEndTime();
         wordTypeService.save();
*/

        //创建单词
        words.forEach(LambdaExceptionUtil.wrapConsumer(item -> youDaoUtil.createWord(item,wordTypeId,roots_and_affixes)));

    }
}
