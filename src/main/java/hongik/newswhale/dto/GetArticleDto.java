package hongik.newswhale.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleDto {

    private Long count;
    private List<ResponseArticle> articleList;
    private List<RecommendArticle> recommendArticleList;

}
