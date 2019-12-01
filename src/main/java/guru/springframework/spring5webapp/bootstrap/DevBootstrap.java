package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Harper Collins", "Ocean Drive");
        Book ddd = new Book("Domain Driven Design", "1234", pub1);
        Book ddd2 = new Book("Domain Driven Design 2", "12346", pub1);

        eric.getBooks().add(ddd);
        eric.getBooks().add(ddd2);
        ddd.getAuthors().add(eric);
        ddd2.getAuthors().add(eric);

        publisherRepository.save(pub1);
        authorRepository.save(eric);
        bookRepository.save(ddd);
        bookRepository.save(ddd2);

        Author rod = new Author("Rod", "Johnson");
        Publisher pub2 = new Publisher("Worx", "St. Mobhi Grove");
        Book noEJB = new Book("J2EE Development without EJB", "23444", pub2);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        publisherRepository.save(pub2);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
