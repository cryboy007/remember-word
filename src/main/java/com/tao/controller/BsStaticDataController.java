package com.tao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tao.entity.BsStaticData;
import com.tao.service.BsStaticDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (BsStaticData)表控制层
 *
 * @author hetao
 * @since 2021-03-19 15:51:00
 */
@RestController
@RequestMapping("bsStaticData")
public class BsStaticDataController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private BsStaticDataService bsStaticDataService;

    /**
     * 分页查询所有数据
     *
     * @param page         分页对象
     * @param bsStaticData 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<BsStaticData> page, BsStaticData bsStaticData) {
        return success(this.bsStaticDataService.page(page, new QueryWrapper<>(bsStaticData)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.bsStaticDataService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param bsStaticData 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody BsStaticData bsStaticData) {
        return success(this.bsStaticDataService.save(bsStaticData));
    }

    /**
     * 修改数据
     *
     * @param bsStaticData 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody BsStaticData bsStaticData) {
        return success(this.bsStaticDataService.updateById(bsStaticData));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.bsStaticDataService.removeByIds(idList));
    }
}