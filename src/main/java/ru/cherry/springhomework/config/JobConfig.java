package ru.cherry.springhomework.config;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.cherry.springhomework.h2.dao.AuthorRepository;
import ru.cherry.springhomework.h2.dao.BookRepository;
import ru.cherry.springhomework.h2.dao.GenreRepository;
import ru.cherry.springhomework.h2.domain.Author;
import ru.cherry.springhomework.h2.domain.Book;
import ru.cherry.springhomework.h2.domain.Genre;
import ru.cherry.springhomework.mongo.domain.AuthorDocument;
import ru.cherry.springhomework.mongo.domain.BookDocument;
import ru.cherry.springhomework.mongo.domain.GenreDocument;

import java.util.HashMap;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    private final int CHUNK_SIZE = 5;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoTemplate mongoTemplate;
    private final ModelMapper mapper;

    public JobConfig(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, MongoTemplate mongoTemplate, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.mongoTemplate = mongoTemplate;
        this.mapper = mapper;
    }

    @Bean
    public ItemReader<Genre> genreReader() {
        return new RepositoryItemReaderBuilder<Genre>()
                .name("genreReader")
                .pageSize(5)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(genreRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemReader<Author> authorReader() {
        return new RepositoryItemReaderBuilder<Author>()
                .name("authorReader")
                .pageSize(5)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(authorRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemReader<Book> bookReader() {
        return new RepositoryItemReaderBuilder<Book>()
                .name("bookReader")
                .pageSize(5)
                .sorts(new HashMap<String, Sort.Direction>())
                .repository(bookRepository)
                .methodName("findAll")
                .build();
    }

    @Bean
    public ItemProcessor<? super Object, ?> authorProcessor() {
        return author -> mapper.map(author, AuthorDocument.class);
    }

    @Bean
    public ItemProcessor<? super Object, ?> genreProcessor() {
        return genre -> mapper.map(genre, GenreDocument.class);
    }

    @Bean
    public ItemProcessor<? super Object, ?> bookProcessor() {
        return book -> mapper.map(book, BookDocument.class);
    }

    @Bean
    public ItemWriter<Author> authorWriter() {
        return new MongoItemWriterBuilder<Author>()
                .collection("authors")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public ItemWriter<Genre> genreWriter() {
        return new MongoItemWriterBuilder<Genre>()
                .collection("genres")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public ItemWriter<Book> bookWriter() {
        return new MongoItemWriterBuilder<Book>()
                .collection("books")
                .template(mongoTemplate)
                .build();
    }

    @Bean
    public Step authorMigrationStep(ItemReader<Author> itemReader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("authorWriter") ItemWriter itemWriter) {
        return stepBuilderFactory.get("authorsMigrationStep")
                .chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step genresMigrationStep(ItemReader<Genre> itemReader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("genreWriter") ItemWriter itemWriter) {
        return stepBuilderFactory.get("genresMigrationStep")
                .chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step bookMigrationStep(ItemReader<Book> itemReader, ItemProcessor<? super Object, ?> itemProcessor, @Qualifier("bookWriter") ItemWriter itemWriter) {
        return stepBuilderFactory.get("booksMigrationStep")
                .chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Job bookMigrationJob() {
        return jobBuilderFactory.get("bookMigrationJob")
                .start(authorMigrationStep(authorReader(), authorProcessor(), authorWriter()))
                .next(genresMigrationStep(genreReader(), genreProcessor(), genreWriter()))
                .next(bookMigrationStep(bookReader(), bookProcessor(), bookWriter()))
                .build();
    }

}
