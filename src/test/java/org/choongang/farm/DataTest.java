package org.choongang.farm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.farm.entities.FarmTourplace;
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

    @Test
    void test1() throws Exception {
        File file = new File("D:/data/data1.json");
        List<Map<String, String>> tmp = om.readValue(file, new TypeReference<>() {});

        List<FarmTourplace> items = tmp.stream()
                .map(d -> FarmTourplace.builder()
                        .title(d.get("title"))
                        .latitude(Double.valueOf(d.get("latitude")))
                        .longitude(Double.valueOf(d.get("longitude")))
                        .tel(d.get("tel"))
                        .address(d.get("address"))
                        .description(d.get("description"))
                        .photoUrl(d.get("photoUrl"))
                        .tourDays(Integer.valueOf(d.get("tourDays")))
                        .build()).toList();

        items.forEach(System.out::println);

    }

}
