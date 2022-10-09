package hongik.newswhale.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTendencyDto {

    private String userName;
    private Long politicsScore;
    private Long economyScore;
    private Long societyScore;
    private Long cultureScore;
    private Long internationalScore;
    private Long localScore;
    private Long sportsScore;
    private Long itScienceScore;

}
