package kz.bzholmyrza.repository;

import kz.bzholmyrza.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //Optional помогает избежать ошибки типа null pointer exception, обертка, если нет в бд, то делает что-то другое
    Optional<Account> findAccountByUsername(String username);
    Optional<Account> findAccountByEmail(String email);
    Optional<Account> findAccountById(Long id);
}
