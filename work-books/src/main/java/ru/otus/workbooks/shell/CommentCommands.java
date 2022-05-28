package ru.otus.workbooks.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.workbooks.service.CommentsService;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {

    private final CommentsService commentsService;

    @ShellMethod(value = "Read comment command", key = {"cr", "cread"})
    public void readComment(@ShellOption() String bookName) {
        commentsService.readComments(bookName);
    }

    @ShellMethod(value = "Create comment command", key = {"cc", "ccreate"})
    public void createComment(@ShellOption() String bookName, @ShellOption() String content) {
        commentsService.createComment(bookName, content);
    }

    @ShellMethod(value = "Update comment command", key = {"cu", "cupdate"})
    public void updateComment(@ShellOption() long id, @ShellOption() String content) {
        commentsService.updateComment(id, content);
    }

    @ShellMethod(value = "Delete comment command", key = {"cd", "cdelete"})
    public void deleteComment(@ShellOption() long id) {
        commentsService.deleteComment(id);
    }

}
