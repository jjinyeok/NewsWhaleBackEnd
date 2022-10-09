package hongik.newswhale.infrastructure.persistance.jpa.repository;

import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    // 쿼리가 수행될 때 LAZY 조회가 아니고 EAGER 조회
    @EntityGraph(attributePaths = "authorities")
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);
}
