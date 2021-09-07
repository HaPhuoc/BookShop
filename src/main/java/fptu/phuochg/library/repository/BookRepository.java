package fptu.phuochg.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fptu.phuochg.library.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
