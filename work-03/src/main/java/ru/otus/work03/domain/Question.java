package ru.otus.work03.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private String questions;
    private String answer;
}
