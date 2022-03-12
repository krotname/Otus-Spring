package ru.otus.work02.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.otus.work02.domain.Question;

import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
@PropertySource(value = "classpath:application.properties")
public class QuestionsDao {

    @Value("${app.csv}")
    private String questions;

    private final CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();

    @SneakyThrows
    public List<Question> getQuestions() {
        final Resource resource = new ClassPathResource(questions);

        final CSVReader reader = new CSVReaderBuilder(
                new FileReader(resource.getFile()))
                .withCSVParser(csvParser)
                .withSkipLines(0)
                .build();

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
