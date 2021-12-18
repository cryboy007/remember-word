package com.tao.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (BsStaticData)表实体类
 *
 * @author hetao
 * @since 2021-03-19 15:51:00
 */
@SuppressWarnings("serial")
@TableName("BS_STATIC_DATA")
public class BsStaticData extends Model<BsStaticData> {

    private String codeType;

    private String codeValue;

    private String codeName;

    private String codeDesc;

    private String codeTypeAlias;

    private Object sortId;

    private String state;

    private String externCodeType;


    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }

    public String getCodeTypeAlias() {
        return codeTypeAlias;
    }

    public void setCodeTypeAlias(String codeTypeAlias) {
        this.codeTypeAlias = codeTypeAlias;
    }

    public Object getSortId() {
        return sortId;
    }

    public void setSortId(Object sortId) {
        this.sortId = sortId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExternCodeType() {
        return externCodeType;
    }

    public void setExternCodeType(String externCodeType) {
        this.externCodeType = externCodeType;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.codeType;
    }
}