package hongik.newswhale.infrastructure.persistance.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hongik.newswhale.application.domain.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @JsonIgnore // 데이터를 주고 받을 때, Ignore 되어서 응답값에 보이지 않게 됨
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성을 DB에 위임 -> id값을 null로 하면 DB가 알아서 AutoIncrement
    private Long userId;

    @Column(name = "username", unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "politics_score")
    private Long politicsScore;

    @Column(name = "economy_score")
    private Long economyScore;

    @Column(name = "society_score")
    private Long societyScore;

    @Column(name = "culture_score")
    private Long cultureScore;

    @Column(name = "international_score")
    private Long internationalScore;

    @Column(name = "local_score")
    private Long localScore;

    @Column(name = "sports_score")
    private Long sportsScore;

    @Column(name = "it_science_score")
    private Long itScienceScore;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<AuthorityEntity> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<UserKeywordEntity> userKeywordEntities;

    public UserEntity(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.politicsScore = user.getPoliticsScore();
        this.economyScore = user.getEconomyScore();
        this.societyScore = user.getSocietyScore();
        this.cultureScore = user.getCultureScore();
        this.internationalScore = user.getInternationalScore();
        this.localScore = user.getLocalScore();
        this.sportsScore = user.getSportsScore();
        this.itScienceScore = user.getItScienceScore();
        this.activated = user.isActivated();
        this.authorities = user.getAuthorities().stream()
                .map(AuthorityEntity::new)
                .collect(Collectors.toSet());
    }

    public User toUser() {
        return User.builder()
                .userId(this.userId)
                .username(this.username)
                .password(this.password)
                .nickname(this.nickname)
                .email(this.email)
                .politicsScore(this.politicsScore)
                .economyScore(this.economyScore)
                .societyScore(this.societyScore)
                .cultureScore(this.cultureScore)
                .internationalScore(this.internationalScore)
                .localScore(this.localScore)
                .sportsScore(this.sportsScore)
                .itScienceScore(this.itScienceScore)
                .activated(this.activated)
                .authorities(this.authorities.stream()
                        .map(AuthorityEntity::toAuthority)
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
