package com.tao.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tao.dao.BsParaDetailDao;
import com.tao.entity.BsParaDetail;
import com.tao.service.BsParaDetailService;
import org.springframework.stereotype.Service;

/**
 * (BsParaDetail)表服务实现类
 *
 * @author hetao
 * @since 2021-03-19 15:47:57
 */
@Service("bsParaDetailService")
@DS("base")
public class BsParaDetailServiceImpl extends ServiceImpl<BsParaDetailDao, BsParaDetail> implements BsParaDetailService {

}