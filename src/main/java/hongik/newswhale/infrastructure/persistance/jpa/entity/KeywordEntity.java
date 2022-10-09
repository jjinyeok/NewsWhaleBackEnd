package hongik.newswhale.infrastructure.persistance.jpa.entity;

import hongik.newswhale.application.domain.Keyword;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "keyword")
public class KeywordEntity {

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
    List<UserKeywordEntity> userKeywordEntities;

    @OneToMany(mappedBy = "keyword", fetch = FetchType.LAZY)
    List<ArticleKeywordEntity> articleKeywordEntities;


    public KeywordEntity(Keyword keyword) {
        this.keywordID = keyword.getKeywordID();
        this.keywordName = keyword.getKeywordName();
        this.politicsCount = keyword.getPoliticsCount();
        this.economyCount = keyword.getEconomyCount();
        this.societyCount = keyword.getSocietyCount();
        this.cultureCount = keyword.getSocietyCount();
        this.internationalCount = keyword.getInternationalCount();
        this.localCount = keyword.getLocalCount();
        this.sportsCount = keyword.getSportsCount();
        this.itScienceCount = keyword.getItScienceCount();
        this.userKeywordEntities = keyword.getUserKeywordEntities();
        this.articleKeywordEntities = keyword.getArticleKeywordEntities();
    }

    public Keyword toKeyword() {
        return Keyword.builder()
                .keywordID(this.keywordID)
                .keywordName(this.keywordName)
                .politicsCount(this.politicsCount)
                .economyCount(this.economyCount)
                .societyCount(this.societyCount)
                .cultureCount(this.cultureCount)
                .internationalCount(this.internationalCount)
                .localCount(this.localCount)
                .sportsCount(this.sportsCount)
                .itScienceCount(this.itScienceCount)
                .userKeywordEntities(this.userKeywordEntities)
                .articleKeywordEntities(this.articleKeywordEntities)
                .build();
    }

}
