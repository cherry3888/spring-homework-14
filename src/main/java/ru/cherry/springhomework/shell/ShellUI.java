package ru.cherry.springhomework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;
import ru.cherry.springhomework.common.MessageService;
import ru.cherry.springhomework.h2.domain.Book;
import ru.cherry.springhomework.h2.service.BookService;
import ru.cherry.springhomework.mongo.domain.BookDocument;
import ru.cherry.springhomework.mongo.service.BookDocumentService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellUI {
    private final MessageService messageService;
    private final BookDocumentService bookDocumentService;
    private final BookService bookService;
    private final JobLauncher jobLauncher;
    private final Job bookMigrationJob;

    //Запуск миграции из H2 в Mongo
    @ShellMethod(value = "Run migration", key = {"mig", "run migration"})
    public void runMigrationFromH2ToMongo() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        messageService.sendMessage("Start migration");
        JobExecution execution = jobLauncher.run(bookMigrationJob, new JobParameters());
        messageService.sendMessage("Finish migration");
    }

    //Книги из Mongo
    @ShellMethod(value = "Get all books from MOngo", key = {"gabm", "get books m"})
    public void gatAllBooksFromMongo() {
        List<BookDocument> bookDocuments = bookDocumentService.getAllBooks();
        if (!CollectionUtils.isEmpty(bookDocuments)) {
            messageService.sendMessage("Книги:");
            bookDocuments.forEach(book -> messageService.sendMessage(book.toString()));
        } else {
            messageService.sendMessage("Ничего не найдено.");
        }
    }

    //Книги из H2
    @ShellMethod(value = "Get all books db from H2", key = {"gabh", "get books h"})
    public void getAllBookFromH2() {
        List<Book> books = bookService.getAllBooks();
        if (!CollectionUtils.isEmpty(books)) {
            messageService.sendMessage("Книги:");
            books.forEach(book -> messageService.sendMessage(book.toString()));
        } else {
            messageService.sendMessage("Ничего не найдено.");
        }
    }
}
