package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_keyword")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private Keyword keyword;

}