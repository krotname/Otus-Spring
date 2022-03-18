package ru.otus.work03.dao;

import lombok.SneakyThrows;
import ru.otus.work03.domain.Question;

import java.util.List;

public interface QuestionsDao {
    @SneakyThrows
    List<Question> getQuestions();
}
