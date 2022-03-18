package ru.otus.work03.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.work03.dao.QuestionsDao;
import ru.otus.work03.domain.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {

    private final QuestionsDao dao;
    private final Locale locale;
    private final MessageSource messageSource;
    private final List<Integer> usesNumber = new ArrayList<>();

    @Override
    public List<Question> getByName() {
        return dao.getQuestions();
    }

    @Override
    public int getRandom() {
        int i;

        do {
            i = (int) (Math.random() * 5);
        }
        while (usesNumber.contains(i));

        usesNumber.add(i);
        return i;
    }

    @Override
    public String getFromLocale(String string) {
        return messageSource.getMessage(string, new String[]{locale.getDisplayName(locale)}, locale);
    }

}
