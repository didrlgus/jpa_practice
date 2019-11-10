package jpapractice;

import jpapractice.model.Book;
import jpapractice.model.BookStore;
import jpapractice.repository.BookRepository;
import jpapractice.repository.BookStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpapracticeApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Test
    void contextLoads() {
        BookStore bookStore = BookStore.builder().name("중앙도서관").build();
        BookStore bookStore1 = BookStore.builder().name("분당도서관").build();

        Book book1 = new Book();
        book1.setName("토비의 스프링3.0");
        book1.setBookStore(bookStore);

        Book book2 = new Book();
        book2.setName("스타트 스프링부트");
        book2.setBookStore(bookStore);

        Book book3 = new Book();
        book3.setName("이펙티브 자바");
        book3.setBookStore(bookStore1);

        Book book4 = new Book();
        book4.setName("스프링 기초");
        book4.setBookStore(bookStore1);

        bookStoreRepository.save(bookStore);
        bookStoreRepository.save(bookStore1);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
}
