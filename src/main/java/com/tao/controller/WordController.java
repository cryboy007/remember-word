package com.tao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.entity.Word;
import com.tao.pojo.vo.BachInputWordVo;
import com.tao.service.WordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * (Word)表控制层
 *
 * @author hetao
 * @since 2021-03-19 16:02:30
 */
@RestController
@RequestMapping("word")
public class WordController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private WordService wordService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param word 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Word> page, Word word) {
        return success(this.wordService.page(page, new QueryWrapper<>(word)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.wordService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param word 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Word word) {
        return success(this.wordService.save(word));
    }

    /**
     * 修改数据
     *
     * @param word 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Word word) {
        return success(this.wordService.updateById(word));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.wordService.removeByIds(idList));
    }

    @PostMapping("batchInputWord")
    public R batchInsertWord(@RequestBody BachInputWordVo bachInputWordVo) throws IOException {
        wordService.batchInsertWord(bachInputWordVo.getWords(),bachInputWordVo.getWordTypeId(),bachInputWordVo.getRootsAndAffixes());
        return success(null);
    }
}
