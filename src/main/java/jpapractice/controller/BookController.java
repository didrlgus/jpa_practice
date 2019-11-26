package jpapractice.controller;

import jpapractice.domain.Book;
import jpapractice.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
*  ManyToOne 단방향 매핑에서 생기는 N+1 과 해결 방법 (Book(N) -> BookStore(1))
 * 1. JPQL 패치조인
 * 2. QueryDsl 패치조인
**/

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/book")
    public ResponseEntity<?> init() {

        // N + 1 문제 발생
        // N : BookStore 개수만큼 검색
        // 1 : Book 검색
        log.info("일반 쿼리 시작");
        List<Book> bookList1 = bookRepository.findAll();
        bookList1.forEach(b->b.getBookStore().getName());
        log.info("일반 쿼리 끝");

        return ResponseEntity.ok("책 찾기 성공2");
    }

    @GetMapping("/book2")
    public ResponseEntity<?> init2() {

        log.info("JPQL 패치조인 시작");
        // 패치 조인하여 LAZE 시 발생하는 N+1 문제 해결 가능
        List<Book> bookList = bookRepository.findAllFetch();    // JPQL 패치조인
        bookList.forEach(b -> b.getBookStore().getName());
        log.info("JPQL 패치조인 끝");

        for(Book b : bookList) log.info("{}",b.getBookStore().getName());

        return ResponseEntity.ok("책 찾기 성공");
    }

    @GetMapping("/book3")
    public ResponseEntity<?> init4() {

        log.info("query dsl fetch 조인 시작");
        List<Book> bookList = bookRepository.getAllBookList();  // QueryDSL 패치조인
        bookList.forEach(b->b.getBookStore().getName());
        log.info("query dsl fetch 조인 끝");

        return ResponseEntity.ok("책 찾기 성공3");
    }
}
