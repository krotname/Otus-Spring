package ru.otus.work03.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.work03.domain.Question;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = QuestionsDaoImpl.class)
class QuestionsDaoTest {

    @MockBean
    private CSVReader csvReader;

    @Autowired
    private QuestionsDao questionsDao;

    @BeforeAll
    private static void init() {
        Locale.setDefault(new Locale("ru", "RU"));
    }

    @Test
    void getQuestions() throws IOException, CsvException {
        List<String[]> list = List.of(
                new String[]{"На берегу какой реки был построен Новгород?", "Волхов"},
                new String[]{"Какую землю открыл Ермак?", "Сибирь"});

        when(csvReader.readAll()).thenReturn(list);

        List<Question> questions = questionsDao.getQuestions();

        assertEquals("На берегу какой реки был построен Новгород?",
                questions.get(0).getQuestions());
        assertEquals("Волхов",
                questions.get(0).getAnswer());
        assertEquals("Какую землю открыл Ермак?",
                questions.get(1).getQuestions());
        assertEquals("Сибирь",
                questions.get(1).getAnswer());
    }
}