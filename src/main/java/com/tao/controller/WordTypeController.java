package com.tao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.entity.WordType;
import com.tao.service.WordTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 词根,词缀表(WordType)表控制层
 *
 * @author hetao
 * @since 2021-03-19 16:02:36
 */
@RestController
@RequestMapping("wordType")
public class WordTypeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WordTypeService wordTypeService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param wordType 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<WordType> page, WordType wordType) {
        return success(this.wordTypeService.page(page, new QueryWrapper<>(wordType)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.wordTypeService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param wordType 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody WordType wordType) {
        return success(this.wordTypeService.save(wordType));
    }

    /**
     * 修改数据
     *
     * @param wordType 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody WordType wordType) {
        return success(this.wordTypeService.updateById(wordType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.wordTypeService.removeByIds(idList));
    }



}
