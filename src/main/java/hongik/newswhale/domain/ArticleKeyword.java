package hongik.newswhale.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ArticleKeyword {

    private Long id;
    private Keyword keyword;
    private Article article;
}
