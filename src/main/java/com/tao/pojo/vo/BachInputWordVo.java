package com.tao.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @ClassName BachInputWordVo
 * @Author HETAO
 * @Date 2021/3/20 11:17
 */
@Data
public class BachInputWordVo {
    private List<String> words ;
    private Integer  wordTypeId;
    private String rootsAndAffixes;
}
