package hongik.newswhale.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteKeywordDto {

    private Long userId;
    private String keywordName;
}
