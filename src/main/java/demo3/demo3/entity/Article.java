package demo3.demo3.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "article_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(name = "article_title")
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

    @OneToMany(mappedBy = "article")
    List<ArticleKeyword> articleKeywords = new ArrayList<ArticleKeyword>();

}
