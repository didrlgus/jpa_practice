package jpapractice.controller;

import jpapractice.model.Book;
import jpapractice.model.BookStore;
import jpapractice.repository.BookRepository;
import jpapractice.repository.BookStoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class JpaController2 {

    private final BookRepository bookRepository;
    private final BookStoreRepository bookStoreRepository;

    @RequestMapping("/library")
    public ResponseEntity<?> init() {

        BookStore bookStore1 = BookStore.builder().name("중앙도서관").build();
        BookStore bookStore2 = BookStore.builder().name("분당도서관").build();
        BookStore bookStore3 = BookStore.builder().name("판교도서관").build();
        BookStore bookStore4 = BookStore.builder().name("야탑도서관").build();

        Book book1 = new Book();
        book1.setName("토비의 스프링3.0");
        book1.setBookStore(bookStore1);

        Book book2 = new Book();
        book2.setName("스타트 스프링부트");
        book2.setBookStore(bookStore1);

        Book book3 = new Book();
        book3.setName("이펙티브 자바");
        book3.setBookStore(bookStore2);

        Book book4 = new Book();
        book4.setName("스프링 기초");
        book4.setBookStore(bookStore2);

        Book book5 = new Book();
        book5.setName("자바의 정석");
        book5.setBookStore(bookStore3);

        Book book6 = new Book();
        book6.setName("JPA 기초");
        book6.setBookStore(bookStore4);

        bookStoreRepository.save(bookStore1);
        bookStoreRepository.save(bookStore2);
        bookStoreRepository.save(bookStore3);
        bookStoreRepository.save(bookStore4);

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);
        bookRepository.save(book5);
        bookRepository.save(book6);

        return ResponseEntity.ok("책 추가 완료");
    }
}
