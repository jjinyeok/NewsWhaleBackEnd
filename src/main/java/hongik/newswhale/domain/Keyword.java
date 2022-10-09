package hongik.newswhale.domain;


import hongik.newswhale.entity.ArticleKeyword;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@Getter
@Builder
@ToString
public class Keyword {

    private Long keywordID;
    private String keywordName;
    private Long politicsCount;
    private Long economyCount;
    private Long societyCount;
    private Long cultureCount;
    private Long internationalCount;
    private Long localCount;
    private Long sportsCount;
    private Long itScienceCount;
    List<hongik.newswhale.entity.UserKeyword> userKeywords;
    List<ArticleKeyword> articleKeywords;
}
