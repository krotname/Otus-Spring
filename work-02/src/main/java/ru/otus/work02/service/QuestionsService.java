package ru.otus.work02.service;

import ru.otus.work02.domain.Question;

import java.util.List;

public interface QuestionsService {
    List<Question> getByName();

    int getRandom();
}
