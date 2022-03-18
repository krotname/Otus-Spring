package ru.otus.work02.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.work02.domain.Question;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:application.properties")
public class TestService {

    private final QuestionsService questionsService;
    @Value("${app.check.min}")
    private int min;
    @Value("${app.check.all}")
    private int all;
    private final Scanner scanner = new Scanner(System.in);

    public void testStudent() {
        List<Question> questionList = questionsService.getByName();
        int numberOfSuccessfulResponses = askQuestions(questionList);
        checkResult(numberOfSuccessfulResponses);
    }

    private int askQuestions(List<Question> questionList) {
        int count = 0;
        for (int i = 0; i < all; i++) {
            Question question = questionList.get(questionsService.getRandom());
            print(question.getQuestions());
            if (question.getAnswer().equalsIgnoreCase(read())) {
                count++;
            }
        }
        return count;
    }

    private void checkResult(int numberOfSuccessfulResponses) {
        if (numberOfSuccessfulResponses > min) {
            print("successful, passed " + numberOfSuccessfulResponses + " out of " + all);
        } else {
            print("failed, passed " + numberOfSuccessfulResponses + " out of " + all);

        }
    }

    private void print(String p) {
        System.out.println(p);
    }

    private String read() {
        return scanner.nextLine();
    }
}
