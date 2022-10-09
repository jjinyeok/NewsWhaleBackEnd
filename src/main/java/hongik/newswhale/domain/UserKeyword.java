package hongik.newswhale.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserKeyword {
    private Long userKeywordId;
    private User user;
    private Keyword keyword;
}
