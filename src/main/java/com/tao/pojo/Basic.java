/**
  * Copyright 2021 bejson.com 
  */
package com.tao.pojo;
import lombok.Data;

import java.util.List;

/**
 * Auto-generated: 2021-03-19 16:8:21
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data

public class Basic {

    private List<String> exam_type;
    private String us_phonetic;
    private String phonetic;
    private String uk_phonetic;
    private List<Wfs> wfs;
    private String uk_speech;
    private List<String> explains;
    private String us_speech;

}