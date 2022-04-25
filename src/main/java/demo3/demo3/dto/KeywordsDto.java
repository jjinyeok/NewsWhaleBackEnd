package demo3.demo3.dto;

import lombok.*;

import java.util.List;

// User가 등록한 키워드 보여주기
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeywordsDto {

    private Long total;
    private List<String> aaa;
}
