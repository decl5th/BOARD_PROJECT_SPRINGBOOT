package org.choongang.farm.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.global.entities.BaseEntity;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
public class FarmTourplace extends BaseEntity {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length=150, nullable = false)
    private String title; // 여행지명
    private Double latitude; // 위도
    private Double longitude; // 경도

    @Column(length=50)
    private String tel; // 연락처

    @Column(length=150)
    private String address; // 주소

    @Lob
    private String description; // 설명

    private String photoUrl; // 사진 파일 주소

    private Integer tourDays; // 여행일


}
