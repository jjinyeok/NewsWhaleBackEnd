package demo3.demo3.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MostKeywordsDto {

    private List<String> politicsKeywords;
    private List<String> economyKeywords;
    private List<String> societyKeywords;
    private List<String> cultureKeywords;
    private List<String> internationalKeywords;
    private List<String> localKeywords;
    private List<String> sportsKeywords;
    private List<String> itScienceKeywords;

}
