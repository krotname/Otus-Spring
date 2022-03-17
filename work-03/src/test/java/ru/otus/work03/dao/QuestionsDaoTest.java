package ru.otus.work03.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@ContextConfiguration(classes = QuestionsDaoImpl.class)
class QuestionsDaoTest {

    @MockBean
    private CSVReader csvReader;

    @Autowired
    private QuestionsDao questionsDao;

    @Test
    void getQuestions() throws IOException, CsvException {
        List<String[]> list = List.of(
                new String[]{"На берегу какой реки был построен Новгород?", "Волхов"},
                new String[]{"Какую землю открыл Ермак?", "Сибирь"});

        when(csvReader.readAll()).thenReturn(list);

        List<Question> questions = questionsDao.getQuestions();

        assertEquals("Question(questions=На берегу какой реки был построен Новгород?, answer=Волхов)",
                questions.get(0).toString());
        assertEquals("Question(questions=Какую землю открыл Ермак?, answer=Сибирь)",
                questions.get(1).toString());
    }
}