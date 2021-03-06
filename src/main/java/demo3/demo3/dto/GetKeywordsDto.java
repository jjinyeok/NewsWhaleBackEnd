package demo3.demo3.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetKeywordsDto {

    private Long count;
    private List<String> keywordName;

}
