package demo3.demo3.repository;

import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.User;
import demo3.demo3.entity.UserKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeyword, Long> {

    List<UserKeyword> findAllByUser(User user);

    Optional<UserKeyword> findByUserAndKeyword(User user, Keyword keyword);
}
