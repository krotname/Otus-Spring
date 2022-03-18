package ru.otus.spring.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.domain.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionsDao {

    private final CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

    private final Resource resource = new ClassPathResource("questions.csv");

    private final CSVReader reader = new CSVReaderBuilder(
            new FileReader(resource.getFile()))
            .withCSVParser(csvParser)
            .withSkipLines(0)
            .build();

    public QuestionsDao() throws IOException {
    }

    @SneakyThrows
    public List<Question> getQuestions() {
        return reader
                .readAll()
                .stream()
                .map(x -> Question
                        .builder()
                        .questions(x[0])
                        .answer(x[1])
                        .build())
                .collect(Collectors.toList());

    }
}
