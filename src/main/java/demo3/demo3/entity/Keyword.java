package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "keyword")
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long keywordID;

    @Column(name = "keyword_name", unique = true)
    private String keywordName;

    @Column(name = "politics_count")
    private Long politicsCount;

    @Column(name = "economy_count")
    private Long economyCount;

    @Column(name = "society_count")
    private Long societyCount;

    @Column(name = "culture_count")
    private Long cultureCount;

    @Column(name = "international_count")
    private Long internationalCount;

    @Column(name = "local_count")
    private Long localCount;

    @Column(name = "sports_count")
    private Long sportsCount;

    @Column(name = "it_science_count")
    private Long itScienceCount;

    @OneToMany(mappedBy = "keyword", fetch = FetchType.LAZY)
    List<UserKeyword> userKeywords = new ArrayList<UserKeyword>();

    @OneToMany(mappedBy = "keyword", fetch = FetchType.LAZY)
    List<ArticleKeyword> articleKeywords = new ArrayList<ArticleKeyword>();

}
