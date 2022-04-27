package demo3.demo3.dto;

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
