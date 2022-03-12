package ru.otus.work02;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.otus.work02.service.TestService;

@Component
@ComponentScan
public class Work02Application {

    public static void main(String[] args) {
        var context =
                new AnnotationConfigApplicationContext(Work02Application.class);

        var bean = context.getBean(TestService.class);

        bean.testStudent();

    }

}
