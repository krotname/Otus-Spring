package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionsService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext
                = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsService bean = classPathXmlApplicationContext.getBean(QuestionsService.class);

        List<Question> questionList = bean.getByName();

        questionList.forEach(System.out::println);

    }
}
