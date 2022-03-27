package ru.otus.work04.service;

import ru.otus.work04.domain.Question;

import java.util.List;

public interface QuestionsService {
    List<Question> getByName();

    int getRandom();

    void resetRandom();

    String getFromLocale(String string);

}
