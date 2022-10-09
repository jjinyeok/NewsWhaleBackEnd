package hongik.newswhale.application.domain;


import hongik.newswhale.infrastructure.persistance.jpa.entity.ArticleKeywordEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@ToString
public class Article {

    private Long articleId;
    private String articleTitle;
    private String articleReporter;
    private String articleUrl;
    private String articleMediaName;
    private String articleMediaUrl;
    private String articleMediaImageSrc;
    private Date articleLastModifiedDate;
    List<ArticleKeywordEntity> articleKeywordEntities;
}


