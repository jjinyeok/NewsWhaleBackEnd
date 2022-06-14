package demo3.demo3.service;

import demo3.demo3.dto.GetUserRecommendKeywordDto;
import demo3.demo3.dto.GetUserTendencyDto;
import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.User;
import demo3.demo3.entity.UserKeyword;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final KeywordRepository keywordRepository;
    private final UserKeywordRepository userKeywordRepository;
    public UserService(UserRepository userRepository, KeywordRepository keywordRepository, UserKeywordRepository userKeywordRepository) {
        this.userRepository = userRepository;
        this.keywordRepository = keywordRepository;
        this.userKeywordRepository = userKeywordRepository;
    }

    public GetUserTendencyDto getUserTendency(String userId) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));
        User user = new User();
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }
        GetUserTendencyDto getUserTendencyDto = GetUserTendencyDto.builder()
                .userName(user.getUsername())
                .politicsScore(user.getPoliticsScore())
                .economyScore(user.getEconomyScore())
                .societyScore(user.getSocietyScore())
                .cultureScore(user.getCultureScore())
                .internationalScore(user.getInternationalScore())
                .localScore(user.getLocalScore())
                .sportsScore(user.getSportsScore())
                .itScienceScore(user.getItScienceScore())
                .build();
        return getUserTendencyDto;
    }

    public GetUserRecommendKeywordDto getUserRecommendKeyword(String userId) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));
        User user = new User();
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }
        List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user);
        TreeMap<Long, String> userTendencyDictionary = new TreeMap<Long, String>();
        userTendencyDictionary.put(user.getPoliticsScore() * (-1), "PoliticsScore");
        userTendencyDictionary.put(user.getEconomyScore() * (-1), "EconomyScore");
        userTendencyDictionary.put(user.getSocietyScore() * (-1), "SocietyScore");
        userTendencyDictionary.put(user.getCultureScore() * (-1), "CultureScore");
        userTendencyDictionary.put(user.getInternationalScore() * (-1), "InternationalScore");
        userTendencyDictionary.put(user.getLocalScore() * (-1), "LocalScore");
        userTendencyDictionary.put(user.getSportsScore() * (-1), "SportsScore");
        userTendencyDictionary.put(user.getItScienceScore() * (-1), "ItScienceScore");

        List<String> keywordNames = new ArrayList<>();

        int count = 0;
        for (String value : userTendencyDictionary.values()) {
            if(count == 0) {
                if(value == "PoliticsScore") {
                    List<Keyword> top10ByOrderByPoliticsCountDesc = keywordRepository.findTop10ByOrderByPoliticsCountDesc();
                    for(Keyword keyword : top10ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<Keyword> top10ByOrderByEconomyCountDesc = keywordRepository.findTop10ByOrderByEconomyCountDesc();
                    for(Keyword keyword : top10ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<Keyword> top10ByOrderBySocietyCountDesc = keywordRepository.findTop10ByOrderBySocietyCountDesc();
                    for(Keyword keyword : top10ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<Keyword> top10ByOrderByCultureCountDesc = keywordRepository.findTop10ByOrderByCultureCountDesc();
                    for(Keyword keyword : top10ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<Keyword> top10ByOrderByInternationalCountDesc = keywordRepository.findTop10ByOrderByInternationalCountDesc();
                    for (Keyword keyword : top10ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<Keyword> top10ByOrderByLocalCountDesc = keywordRepository.findTop10ByOrderByLocalCountDesc();
                    for (Keyword keyword : top10ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<Keyword> top10ByOrderBySportsCountDesc = keywordRepository.findTop10ByOrderBySportsCountDesc();
                    for (Keyword keyword : top10ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<Keyword> top10ByOrderByItScienceCountDesc = keywordRepository.findTop10ByOrderByItScienceCountDesc();
                    for (Keyword keyword : top10ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                }
            } else if (count == 1) {
                if(value == "PoliticsScore") {
                    List<Keyword> top5ByOrderByPoliticsCountDesc = keywordRepository.findTop5ByOrderByPoliticsCountDesc();
                    for(Keyword keyword : top5ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<Keyword> top5ByOrderByEconomyCountDesc = keywordRepository.findTop5ByOrderByEconomyCountDesc();
                    for(Keyword keyword : top5ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<Keyword> top5ByOrderBySocietyCountDesc = keywordRepository.findTop5ByOrderBySocietyCountDesc();
                    for(Keyword keyword : top5ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<Keyword> top5ByOrderByCultureCountDesc = keywordRepository.findTop5ByOrderByCultureCountDesc();
                    for(Keyword keyword : top5ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<Keyword> top5ByOrderByInternationalCountDesc = keywordRepository.findTop5ByOrderByInternationalCountDesc();
                    for (Keyword keyword : top5ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<Keyword> top5ByOrderByLocalCountDesc = keywordRepository.findTop5ByOrderByLocalCountDesc();
                    for (Keyword keyword : top5ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<Keyword> top5ByOrderBySportsCountDesc = keywordRepository.findTop5ByOrderBySportsCountDesc();
                    for (Keyword keyword : top5ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<Keyword> top5ByOrderByItScienceCountDesc = keywordRepository.findTop5ByOrderByItScienceCountDesc();
                    for (Keyword keyword : top5ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                }
            } else if (count == 2) {
                if(value == "PoliticsScore") {
                    List<Keyword> top3ByOrderByPoliticsCountDesc = keywordRepository.findTop3ByOrderByPoliticsCountDesc();
                    for(Keyword keyword : top3ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<Keyword> top3ByOrderByEconomyCountDesc = keywordRepository.findTop3ByOrderByEconomyCountDesc();
                    for(Keyword keyword : top3ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<Keyword> top3ByOrderBySocietyCountDesc = keywordRepository.findTop3ByOrderBySocietyCountDesc();
                    for(Keyword keyword : top3ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<Keyword> top3ByOrderByCultureCountDesc = keywordRepository.findTop3ByOrderByCultureCountDesc();
                    for(Keyword keyword : top3ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<Keyword> top3ByOrderByInternationalCountDesc = keywordRepository.findTop3ByOrderByInternationalCountDesc();
                    for (Keyword keyword : top3ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<Keyword> top3ByOrderByLocalCountDesc = keywordRepository.findTop3ByOrderByLocalCountDesc();
                    for (Keyword keyword : top3ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<Keyword> top3ByOrderBySportsCountDesc = keywordRepository.findTop3ByOrderBySportsCountDesc();
                    for (Keyword keyword : top3ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<Keyword> top3ByOrderByItScienceCountDesc = keywordRepository.findTop3ByOrderByItScienceCountDesc();
                    for (Keyword keyword : top3ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeyword userKeyword : userKeywords) {
                            if(userKeyword.getKeyword() == keyword) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keyword.getKeywordName())) {
                                keywordNames.add(keyword.getKeywordName());
                            }
                        }
                    }
                }
            }
            count += 1;
        }
        GetUserRecommendKeywordDto getUserRecommendKeywordDto = GetUserRecommendKeywordDto.builder()
                .count(Long.valueOf(keywordNames.size()))
                .recommendKeywords(keywordNames)
                .build();
        return getUserRecommendKeywordDto;

    }
}
