package com.tao.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.dao.BsStaticDataDao;
import com.tao.entity.BsStaticData;
import com.tao.service.BsStaticDataService;
import org.springframework.stereotype.Service;

/**
 * (BsStaticData)表服务实现类
 *
 * @author hetao
 * @since 2021-03-19 15:51:00
 */
@Service("bsStaticDataService")
@DS("base")
public class BsStaticDataServiceImpl extends ServiceImpl<BsStaticDataDao, BsStaticData> implements BsStaticDataService {

}