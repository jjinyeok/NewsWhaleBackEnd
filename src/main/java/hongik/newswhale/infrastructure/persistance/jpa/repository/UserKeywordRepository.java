package hongik.newswhale.infrastructure.persistance.jpa.repository;

import hongik.newswhale.infrastructure.persistance.jpa.entity.KeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserKeywordRepository extends JpaRepository<UserKeywordEntity, Long> {

    List<UserKeywordEntity> findAllByUser(UserEntity userEntity);

    Optional<UserKeywordEntity> findByUserAndKeyword(UserEntity userEntity, KeywordEntity keywordEntity);
}
