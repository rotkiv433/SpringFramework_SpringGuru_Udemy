package guru.springframework.demo.bootstrap;

import guru.springframework.demo.model.Author;
import guru.springframework.demo.model.Book;
import guru.springframework.demo.model.Publisher;
import guru.springframework.demo.repositories.AuthorRepository;
import guru.springframework.demo.repositories.BookRepository;
import guru.springframework.demo.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Publisher publisher1 = new Publisher("foo", "garbage");
        Publisher publisher2 = new Publisher("foo2", "trash");

        //Eric
        Author eric = new Author("Eric", "Evans");

        Book aaa = new Book("Domain Driven Design", "1234", publisher1);
        eric.getBooks().add(aaa);
        aaa.getAuthors().add(eric);

        publisherRepository.save(publisher1);
        authorRepository.save(eric);
        bookRepository.save(aaa);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book bbb = new Book("J2EE Development withour EJB", "234444",publisher2);
        rod.getBooks().add(bbb);
        bbb.getAuthors().add(rod);

        publisherRepository.save(publisher2);
        authorRepository.save(rod);
        bookRepository.save(bbb);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
