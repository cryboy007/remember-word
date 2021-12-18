package com.tao.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 词根,词缀表(WordType)表实体类
 *
 * @author hetao
 * @since 2021-03-19 16:02:35
 */
@SuppressWarnings("serial")
@Data
@TableName("word_type")
public class WordType extends Model<WordType> {

    private Integer id;

    private String word;

    private Integer wordType;

    private Integer sort;

    private String wordDesc;

    private Integer amount;

    private Date createTime;

    private Date endTime;


}
