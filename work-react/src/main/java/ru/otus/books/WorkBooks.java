package ru.otus.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class WorkBooks {

    public static void main(String[] args) {
        SpringApplication.run(WorkBooks.class);
    }

}
