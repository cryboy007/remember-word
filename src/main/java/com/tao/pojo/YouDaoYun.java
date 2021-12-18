/**
  * Copyright 2021 bejson.com 
  */
package com.tao.pojo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Auto-generated: 2021-03-19 16:8:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@ToString
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class YouDaoYun {

    private List<String> returnPhrase;
    private String query;
    private String errorCode;
    private String l;
    @JsonAlias("tSpeakUrl")
    private String tSpeakUrl;
    private List<Web> web;
    private String requestId;
    private List<String> translation;
    private Dict dict;
    private Webdict webdict;
    private Basic basic;
    @JsonAlias("isWord")
    private Boolean isWord;
    private String speakUrl;

}