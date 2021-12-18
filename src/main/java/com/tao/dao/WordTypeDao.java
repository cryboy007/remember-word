package com.tao.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tao.entity.WordType;

/**
 * 词根,词缀表(WordType)表数据库访问层
 *
 * @author hetao
 * @since 2021-03-19 16:02:35
 */
public interface WordTypeDao extends BaseMapper<WordType> {
    void updateWordTypeCountById(WordType wordType);
}
