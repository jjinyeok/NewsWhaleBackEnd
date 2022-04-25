package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "news_keyword")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_keyword_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

}
