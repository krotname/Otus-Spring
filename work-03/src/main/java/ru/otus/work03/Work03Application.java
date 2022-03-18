package ru.otus.work03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.work03.service.TestService;

@SpringBootApplication
public class Work03Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Work03Application.class, args);

        var bean = context.getBean(TestService.class);

        bean.testStudent();
    }

}
