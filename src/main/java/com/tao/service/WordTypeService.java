package com.tao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tao.entity.WordType;
import com.tao.pojo.vo.WordList;

import java.util.List;

/**
 * 词根,词缀表(WordType)表服务接口
 *
 * @author hetao
 * @since 2021-03-19 16:02:36
 */
public interface WordTypeService extends IService<WordType> {
    List<WordList> loadContent();
}
