package jpapractice.repository;

import jpapractice.domain.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<BookStore, Long> {
}
