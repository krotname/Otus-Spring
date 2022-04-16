package ru.otus.workbooks.service;

import org.springframework.stereotype.Service;

@Service
public class IOServiceConsole implements IOService {
    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
