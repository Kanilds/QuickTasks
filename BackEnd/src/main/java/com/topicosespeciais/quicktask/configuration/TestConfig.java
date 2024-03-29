package com.topicosespeciais.quicktask.configuration;

import com.topicosespeciais.quicktask.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instancia() throws ParseException {
        this.dbService.instaciaBaseDados();
        return true;
    }
}
