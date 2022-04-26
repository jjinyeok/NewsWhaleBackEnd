package demo3.demo3.dto;

import demo3.demo3.entity.News;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNewsDto {

    private Long count;
    private List<ResponseNews> newsList;

}
