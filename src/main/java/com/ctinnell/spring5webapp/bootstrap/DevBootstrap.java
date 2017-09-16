package com.ctinnell.spring5webapp.bootstrap;

import com.ctinnell.spring5webapp.model.Author;
import com.ctinnell.spring5webapp.model.Book;
import com.ctinnell.spring5webapp.model.Publisher;
import com.ctinnell.spring5webapp.repositories.AuthorRepository;
import com.ctinnell.spring5webapp.repositories.BookRepository;
import com.ctinnell.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    //Spring will inject the repositories into this class. Spring will autowire these.
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
        //Eric
        Author eric = new Author("Eric", "Evans");

        Publisher publisher = new Publisher("Harper Collins");
        publisher.setAddress1("195 Broadway");
        publisher.setAddress2("");
        publisher.setCity("New York");
        publisher.setState("New York");
        publisher.setZip("10007");
        publisher.setZipExtension("000");
        publisherRepository.save(publisher);
        Book ddd = new Book("Domain Driven Design", "1234", publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");

        Publisher publisher2 = new Publisher("Penguin Random House");
        publisher2.setAddress1("1019 IN-47");
        publisher2.setAddress2("");
        publisher2.setCity("Crawfordsville");
        publisher2.setState("IN");
        publisher2.setZip("47933");
        publisher2.setZipExtension("000");
        publisherRepository.save(publisher2);
        Book noEJB = new Book("J2EE Development without EJB", "23444", publisher2);
        rod.getBooks().add(noEJB);


        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
