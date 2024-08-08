package org.choongang.farm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.farm.entities.FarmTourplace;
import org.choongang.farm.repositories.FarmTourPlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.util.List;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
public class DataTest {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FarmTourPlaceRepository repository;

    @Test
    void test1() throws Exception {
        File file = new File("D:/data/data1.json");
        List<Map<String, String>> tmp = om.readValue(file, new TypeReference<>() {});

        List<FarmTourplace> items = tmp.stream()
                .map(d -> FarmTourplace.builder()
                        .title(d.get("관광지명"))
                        .latitude(Double.valueOf(d.get("위도")))
                        .longitude(Double.valueOf(d.get("경도")))
                        .tel(d.get("전화번호"))
                        .address(d.get("주소"))
                        .description(d.get("설명"))
                        .photoUrl(d.get("사진"))
                        .tourDays(Integer.valueOf(d.get("여행일")))
                        .build()).toList();

       repository.saveAllAndFlush(items);

    }

}
