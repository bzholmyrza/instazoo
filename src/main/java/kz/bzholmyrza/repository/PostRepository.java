package kz.bzholmyrza.repository;

import kz.bzholmyrza.entity.Account;
import kz.bzholmyrza.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAccountOrderByCreatedDateCreatedDateDesc(Account account);
    List<Post> findAllByOrderByCreatedDateDesc();
    Optional<Post> findPostByIdAndAccount(Long id, Account account);
}
