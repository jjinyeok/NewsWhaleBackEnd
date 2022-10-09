package hongik.newswhale.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecommendArticle {

    private String articleTitle;
    private String articleUrl;

}
