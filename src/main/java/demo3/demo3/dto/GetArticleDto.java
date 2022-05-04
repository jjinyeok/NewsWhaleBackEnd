package demo3.demo3.dto;

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

}
