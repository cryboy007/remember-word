package com.tao.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * (Word)表实体类
 *
 * @author hetao
 * @since 2021-03-19 16:02:30
 */
@SuppressWarnings("serial")
public class Word extends Model<Word> {

    private Integer id;

    private String learningWord;

    private String explaination;

    private String pronunciation;

    private String sentence;

    private String phonogram;

    private String nature;

    private String sentenceExp;

    private Date addTime;

    private Date updateTime;

    private Integer wordType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLearningWord() {
        return learningWord;
    }

    public void setLearningWord(String learningWord) {
        this.learningWord = learningWord;
    }

    public String getExplaination() {
        return explaination;
    }

    public void setExplaination(String explaination) {
        this.explaination = explaination;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getPhonogram() {
        return phonogram;
    }

    public void setPhonogram(String phonogram) {
        this.phonogram = phonogram;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getSentenceExp() {
        return sentenceExp;
    }

    public void setSentenceExp(String sentenceExp) {
        this.sentenceExp = sentenceExp;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getWordType() {
        return wordType;
    }

    public void setWordType(Integer wordType) {
        this.wordType = wordType;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
