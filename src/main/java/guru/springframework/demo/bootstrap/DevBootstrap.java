package guru.springframework.demo.bootstrap;

import guru.springframework.demo.model.Author;
import guru.springframework.demo.model.Book;
import guru.springframework.demo.repositories.AuthorRepository;
import guru.springframework.demo.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book aaa = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(aaa);
        aaa.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(aaa);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book bbb = new Book("J2EE Development withour EJB", "234444", "Worx");
        rod.getBooks().add(bbb);
        bbb.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(bbb);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
