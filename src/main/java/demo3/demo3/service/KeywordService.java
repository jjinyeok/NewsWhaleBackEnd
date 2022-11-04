package demo3.demo3.service;

import demo3.demo3.dto.MostKeywordsDto;
import demo3.demo3.entity.Keyword;
import demo3.demo3.repository.KeywordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;
    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public MostKeywordsDto getMostKeywords() {

        List<Keyword> politicsKeywords = keywordRepository.findTop3ByOrderByPoliticsCountDesc();
        List<String> politicsKeywordsNames = new ArrayList<>();
        for(Keyword politicsKeyword: politicsKeywords){
            politicsKeywordsNames.add(politicsKeyword.getKeywordName());
        }

        List<Keyword> economyKeywords = keywordRepository.findTop3ByOrderByEconomyCountDesc();
        List<String> economyKeywordsNames = new ArrayList<>();
        for(Keyword economyKeyword: economyKeywords){
            economyKeywordsNames.add(economyKeyword.getKeywordName());
        }

        List<Keyword> societyKeywords = keywordRepository.findTop3ByOrderBySocietyCountDesc();
        List<String> societyKeywordsNames = new ArrayList<>();
        for(Keyword societyKeyword: societyKeywords){
            societyKeywordsNames.add(societyKeyword.getKeywordName());
        }

        List<Keyword> cultureKeywords = keywordRepository.findTop3ByOrderByCultureCountDesc();
        List<String> cultureKeywordsNames = new ArrayList<>();
        for(Keyword cultureKeyword: cultureKeywords){
            cultureKeywordsNames.add(cultureKeyword.getKeywordName());
        }

        List<Keyword> internationalKeywords = keywordRepository.findTop3ByOrderByInternationalCountDesc();
        List<String> internationalKeywordsNames = new ArrayList<>();
        for(Keyword internationalKeyword: internationalKeywords){
            internationalKeywordsNames.add(internationalKeyword.getKeywordName());
        }

        List<Keyword> localKeywords = keywordRepository.findTop3ByOrderByLocalCountDesc();
        List<String> localKeywordsNames = new ArrayList<>();
        for(Keyword localKeyword: localKeywords){
            localKeywordsNames.add(localKeyword.getKeywordName());
        }

        List<Keyword> sportsKeywords = keywordRepository.findTop3ByOrderBySportsCountDesc();
        List<String> sportsKeywordsNames = new ArrayList<>();
        for(Keyword sportsKeyword: sportsKeywords){
            sportsKeywordsNames.add(sportsKeyword.getKeywordName());
        }

        List<Keyword> itScienceKeywords = keywordRepository.findTop3ByOrderByItScienceCountDesc();
        List<String> itScienceKeywordsNames = new ArrayList<>();
        for(Keyword itScienceKeyword: itScienceKeywords){
            itScienceKeywordsNames.add(itScienceKeyword.getKeywordName());
        }

        MostKeywordsDto mostKeywordsDto = MostKeywordsDto.builder()
                .politicsKeywords(politicsKeywordsNames)
                .economyKeywords(economyKeywordsNames)
                .societyKeywords(societyKeywordsNames)
                .cultureKeywords(cultureKeywordsNames)
                .internationalKeywords(internationalKeywordsNames)
                .localKeywords(localKeywordsNames)
                .sportsKeywords(sportsKeywordsNames)
                .itScienceKeywords(itScienceKeywordsNames)
                .build();

        return mostKeywordsDto;
    }
}
