package ru.otus.work04.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.work04.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final TestService testService;

    @ShellMethod(value = "Test command", key = {"t", "test"})
    public void login() {
        testService.testStudent();
    }

}
