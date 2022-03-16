package ru.otus.work02.dao;

import lombok.SneakyThrows;
import ru.otus.work02.domain.Question;

import java.util.List;

public interface QuestionsDao {
    @SneakyThrows
    List<Question> getQuestions();
}
