package demo3.demo3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

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
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List <UserKeyword> userKeywords = new ArrayList<UserKeyword>();

}
