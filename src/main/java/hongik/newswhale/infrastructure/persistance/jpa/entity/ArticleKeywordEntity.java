package hongik.newswhale.infrastructure.persistance.jpa.entity;

import hongik.newswhale.application.domain.ArticleKeyword;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "article_keyword")
public class ArticleKeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_keyword_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private KeywordEntity keywordEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEntity articleEntity;


    public ArticleKeywordEntity(ArticleKeyword articleKeyword) {
        this.id = articleKeyword.getId();
        this.keywordEntity = new KeywordEntity(articleKeyword.getKeyword());
        this.articleEntity = new ArticleEntity(articleKeyword.getArticle());
    }

    public ArticleKeyword toArticleKeyword(){
        return ArticleKeyword.builder()
                .id(this.id)
                .keyword(this.keywordEntity.toKeyword())
                .article(this.articleEntity.toArticle())
                .build();
    }

}
