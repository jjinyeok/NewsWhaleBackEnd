package hongik.newswhale.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostKeywordDto {

    private Long userId;
    private String keywordName;
    private Boolean success;

}
