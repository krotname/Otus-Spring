package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionsService {

    private final QuestionsDao dao;

    public QuestionsService(QuestionsDao dao) {
        this.dao = dao;
    }

    public List<Question> getByName() {
        return dao.getQuestions();
    }

}
