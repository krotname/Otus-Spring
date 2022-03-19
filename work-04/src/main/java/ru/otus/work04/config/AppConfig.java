package ru.otus.work04.config;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

@Configuration
public class AppConfig {

    @Value("${app.csv}")
    private String questions;

    @Bean
    public Resource getLocaleResource(Locale locale) {
        return new ClassPathResource(questions + "_" + locale.getLanguage() + ".csv");
    }

    @Bean
    public CSVParser getCSVParser() {
        return new CSVParserBuilder().withSeparator(';').build();
    }

    @Bean
    public CSVReader getCSVReader(CSVParser csvParser, Resource resource) throws IOException {
        return new CSVReaderBuilder(
                new FileReader(resource.getFile()))
                .withCSVParser(csvParser)
                .withSkipLines(0)
                .build();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:locale\\q");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public Locale getLocale() {
        return Locale.getDefault();
    }

}
