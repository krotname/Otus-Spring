package ru.otus.work03.service;

import ru.otus.work03.domain.Question;

import java.util.List;

public interface QuestionsService {
    List<Question> getByName();

    int getRandom();

    String getFromLocale(String string);

}
