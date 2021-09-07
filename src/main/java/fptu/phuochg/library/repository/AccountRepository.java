package fptu.phuochg.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fptu.phuochg.library.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

}
