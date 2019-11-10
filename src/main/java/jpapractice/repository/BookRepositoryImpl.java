package jpapractice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jpapractice.model.Book;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static jpapractice.QBook.book;
import static jpapractice.QBookStore.bookStore;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Book> getAllBookList() {
        return queryFactory.selectFrom(book)
                .leftJoin(book.bookStore, bookStore)
                .fetchJoin()
                .fetch();
    }
}
