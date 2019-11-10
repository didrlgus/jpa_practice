package jpapractice.repository;

import jpapractice.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    @Query("select b from Book b join fetch b.bookStore")
    List<Book> findAllFetch();
}
