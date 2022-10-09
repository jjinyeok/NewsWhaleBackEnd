package hongik.newswhale.application.domain;


import hongik.newswhale.infrastructure.persistance.jpa.entity.ArticleKeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserKeywordEntity;
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
    List<UserKeywordEntity> userKeywordEntities;
    List<ArticleKeywordEntity> articleKeywordEntities;
}
