package hongik.newswhale.infrastructure.persistance.jpa.entity;

import hongik.newswhale.application.domain.UserKeyword;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "user_keyword")
public class UserKeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_keyword_id")
    private Long userKeywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private KeywordEntity keywordEntity;


    public UserKeywordEntity(UserKeyword userKeyword) {
        this.userKeywordId = userKeyword.getUserKeywordId();
        this.userEntity = new UserEntity(userKeyword.getUser());
        this.keywordEntity = new KeywordEntity(userKeyword.getKeyword());
    }

    public UserKeyword toUserKeyword() {
        return UserKeyword.builder()
                .userKeywordId(this.userKeywordId)
                .user(this.userEntity.toUser())
                .keyword(this.keywordEntity.toKeyword())
                .build();
    }

}
