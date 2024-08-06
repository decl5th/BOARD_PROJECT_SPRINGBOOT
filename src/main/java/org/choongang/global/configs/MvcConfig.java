package org.choongang.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableJpaAuditing
@Configuration
@EnableScheduling

public class MvcConfig implements WebMvcConfigurer {

    /**
     * <input type="hidden" name="_method" value="patch"> -> patch 방식으로 요청
     * ?_method=DELETE
     * @return
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
        return new HiddenHttpMethodFilter();
    }
}