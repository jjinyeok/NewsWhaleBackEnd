package hongik.newswhale.domain;


import hongik.newswhale.entity.ArticleKeyword;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
    List<ArticleKeyword> articleKeywords;
}


