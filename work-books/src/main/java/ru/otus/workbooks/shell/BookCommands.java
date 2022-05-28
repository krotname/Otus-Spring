package ru.otus.workbooks.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.workbooks.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {

    private final BookService bookService;

    @ShellMethod(value = "Info command", key = {"i", "info"})
    public void info() {
        bookService.printAllInfo();
    }

    @ShellMethod(value = "Read book command", key = {"r", "read"})
    public void readBook(@ShellOption({"r", "read"}) String book) {
        bookService.printBook(book);
    }

    @ShellMethod(value = "Create book command", key = {"c", "create"})
    public void createBook(@ShellOption({"n", "name"}) String name, @ShellOption({"genre"}) int genre, @ShellOption({"author"}) int author) {
        bookService.createBook(name, genre, author);
    }

    @ShellMethod(value = "Update book command", key = {"u", "update"})
    public void updateBook(@ShellOption({"n", "name"}) String name, @ShellOption({"genre"}) int genre, @ShellOption({"author"}) int author) {
        bookService.updateBook(name, genre, author);
    }

    @ShellMethod(value = "Delete book command", key = {"d", "delete"})
    public void deleteBook(@ShellOption({"n", "name"}) String name) {
        bookService.deleteBook(name);
    }
}
