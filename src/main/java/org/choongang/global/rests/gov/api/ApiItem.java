package org.choongang.global.rests.gov.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiItem {
    private String addr1;
    private String addr2;
    private String areacode;
    private String booktour;
    private String cat1;
    private String cat2; // 2차분류
    private String cat3; // 3차분류
    private Long contentid;
    private Long contenttypeid;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime createdtime;
    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime modifiedtime;
    private Double dist;
    private String firstimage;
    private String firstimage2;
    private Double mapx;
    private Double mapy;
    private Integer mlevel;
    private Integer sigungucode;
    private String tel;
    private String title;

}
