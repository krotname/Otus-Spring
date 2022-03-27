package ru.otus.work04.dao;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import ru.otus.work04.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QuestionsDaoImpl implements QuestionsDao {

    private final CSVReader csvReader;

    private List<Question> collect;

    @Override
    @SneakyThrows
    public List<Question> getQuestions() {

        if (collect == null) {
            collect = csvReader
                    .readAll()
                    .stream()
                    .map(x -> Question
                            .builder()
                            .questions(x[0])
                            .answer(x[1])
                            .build())
                    .collect(Collectors.toList());
        }
        return collect;

    }
}
