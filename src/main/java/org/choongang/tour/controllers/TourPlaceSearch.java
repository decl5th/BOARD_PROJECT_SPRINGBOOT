package org.choongang.tour.controllers;

import lombok.Data;

@Data
public class TourPlaceSearch {
    private Double latitude;
    private Double longitude;
    private Integer radius = 1000; // 1000m = 1km, 반경을 선택할 수 있게 추후에 설정
}
