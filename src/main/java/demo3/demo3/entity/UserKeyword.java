package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_keyword_id")
    private Long userKeywordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword; // 나중에 Keyword 객체로 바꿔줘야함

}
