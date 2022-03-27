package ru.otus.work04.dao;

import lombok.SneakyThrows;
import ru.otus.work04.domain.Question;

import java.util.List;

public interface QuestionsDao {
    @SneakyThrows
    List<Question> getQuestions();
}
