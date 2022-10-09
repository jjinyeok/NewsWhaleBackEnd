package hongik.newswhale.infrastructure.persistance.jpa.entity;

import hongik.newswhale.application.domain.Article;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(name = "article_title", unique = true)
    private String articleTitle;

    @Column(name = "article_reporter")
    private String articleReporter;

    @Column(name = "article_url")
    private String articleUrl;

    @Column(name = "article_media_name")
    private String articleMediaName;

    @Column(name = "article_media_url")
    private String articleMediaUrl;

    @Column(name = "article_media_image_src")
    private String articleMediaImageSrc;

    @Column(name = "article_last_modified_date")
    private Date articleLastModifiedDate;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    List<ArticleKeywordEntity> articleKeywordEntities;

    public ArticleEntity(Article article){
        this.articleId = article.getArticleId();
        this.articleTitle = article.getArticleTitle();
        this.articleReporter = article.getArticleReporter();
        this.articleUrl = article.getArticleUrl();
        this.articleMediaName = article.getArticleMediaName();
        this.articleMediaUrl = article.getArticleMediaUrl();
        this.articleMediaImageSrc = article.getArticleMediaImageSrc();
        this.articleLastModifiedDate = article.getArticleLastModifiedDate();
        this.articleKeywordEntities = article.getArticleKeywordEntities();
    }

    public Article toArticle(){
        return Article.builder()
                .articleId(this.articleId)
                .articleTitle(this.articleTitle)
                .articleReporter(this.articleReporter)
                .articleUrl(this.articleUrl)
                .articleMediaName(this.articleMediaName)
                .articleMediaUrl(this.articleMediaUrl)
                .articleMediaImageSrc(this.articleMediaImageSrc)
                .articleLastModifiedDate(this.articleLastModifiedDate)
                .articleKeywordEntities(this.articleKeywordEntities)
                .build();

    }

}
