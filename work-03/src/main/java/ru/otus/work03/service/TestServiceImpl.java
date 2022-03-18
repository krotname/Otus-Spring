package ru.otus.work03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.work03.domain.Question;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final QuestionsService questionsService;

    @Value("${app.check.min}")
    private int min;

    @Value("${app.check.all}")
    private int all;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void testStudent() {

        print(questionsService.getFromLocale("welcome"));
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
            print(questionsService.getFromLocale("successful") + numberOfSuccessfulResponses + questionsService.getFromLocale("outof") + all);
        } else {
            print(questionsService.getFromLocale("failed")  + numberOfSuccessfulResponses + questionsService.getFromLocale("outof") + all);
        }
    }

    private void print(String p) {
        System.out.println(p);
    }

    private String read() {
        return scanner.nextLine();
    }
}
