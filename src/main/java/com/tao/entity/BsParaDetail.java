package com.tao.entity;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * (BsParaDetail)表实体类
 *
 * @author hetao
 * @since 2021-03-19 15:47:52
 */
@SuppressWarnings("serial")
@TableName("BS_PARA_DETAIL")
public class BsParaDetail extends Model<BsParaDetail> {
    //地市
    private String regionId;
    //参数类型
    private String paraType;
    //参数名称
    private String paraName;
    //参数名称
    private String paraCode;

    private String para1;

    private String para2;

    private String para3;

    private String para4;

    private String para5;
    //参数描述
    private String paraDesc;
    //记录状态
    private String state;
    //时间
    private Object stateDate;
    //描述
    private String remarks;


    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getParaType() {
        return paraType;
    }

    public void setParaType(String paraType) {
        this.paraType = paraType;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getPara3() {
        return para3;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    public String getPara4() {
        return para4;
    }

    public void setPara4(String para4) {
        this.para4 = para4;
    }

    public String getPara5() {
        return para5;
    }

    public void setPara5(String para5) {
        this.para5 = para5;
    }

    public String getParaDesc() {
        return paraDesc;
    }

    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getStateDate() {
        return stateDate;
    }

    public void setStateDate(Object stateDate) {
        this.stateDate = stateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}