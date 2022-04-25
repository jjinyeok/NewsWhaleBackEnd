package demo3.demo3.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteKeywordDto {

    private Long userId;
    private String keywordDto;
}
