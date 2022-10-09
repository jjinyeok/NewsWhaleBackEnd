/*
package hongik.newswhale.application.service;

import hongik.newswhale.dto.GetUserRecommendKeywordDto;
import hongik.newswhale.dto.GetUserTendencyDto;
import hongik.newswhale.infrastructure.persistance.jpa.entity.KeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserKeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.repository.KeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserKeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserRepository;
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
        Optional<UserEntity> optionalUser = userRepository.findById(Long.parseLong(userId));
        UserEntity userEntity = new UserEntity();
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }
        GetUserTendencyDto getUserTendencyDto = GetUserTendencyDto.builder()
                .userName(userEntity.getUsername())
                .politicsScore(userEntity.getPoliticsScore())
                .economyScore(userEntity.getEconomyScore())
                .societyScore(userEntity.getSocietyScore())
                .cultureScore(userEntity.getCultureScore())
                .internationalScore(userEntity.getInternationalScore())
                .localScore(userEntity.getLocalScore())
                .sportsScore(userEntity.getSportsScore())
                .itScienceScore(userEntity.getItScienceScore())
                .build();
        return getUserTendencyDto;
    }

    public GetUserRecommendKeywordDto getUserRecommendKeyword(String userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(Long.parseLong(userId));
        UserEntity userEntity = new UserEntity();
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }
        List<UserKeywordEntity> userKeywordEntities = userKeywordRepository.findAllByUser(userEntity);
        TreeMap<Long, String> userTendencyDictionary = new TreeMap<Long, String>();
        userTendencyDictionary.put(userEntity.getPoliticsScore() * (-1), "PoliticsScore");
        userTendencyDictionary.put(userEntity.getEconomyScore() * (-1), "EconomyScore");
        userTendencyDictionary.put(userEntity.getSocietyScore() * (-1), "SocietyScore");
        userTendencyDictionary.put(userEntity.getCultureScore() * (-1), "CultureScore");
        userTendencyDictionary.put(userEntity.getInternationalScore() * (-1), "InternationalScore");
        userTendencyDictionary.put(userEntity.getLocalScore() * (-1), "LocalScore");
        userTendencyDictionary.put(userEntity.getSportsScore() * (-1), "SportsScore");
        userTendencyDictionary.put(userEntity.getItScienceScore() * (-1), "ItScienceScore");

        List<String> keywordNames = new ArrayList<>();

        int count = 0;
        for (String value : userTendencyDictionary.values()) {
            if(count == 0) {
                if(value == "PoliticsScore") {
                    List<KeywordEntity> top10ByOrderByPoliticsCountDesc = keywordRepository.findTop10ByOrderByPoliticsCountDesc();
                    for(KeywordEntity keywordEntity : top10ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<KeywordEntity> top10ByOrderByEconomyCountDesc = keywordRepository.findTop10ByOrderByEconomyCountDesc();
                    for(KeywordEntity keywordEntity : top10ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<KeywordEntity> top10ByOrderBySocietyCountDesc = keywordRepository.findTop10ByOrderBySocietyCountDesc();
                    for(KeywordEntity keywordEntity : top10ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<KeywordEntity> top10ByOrderByCultureCountDesc = keywordRepository.findTop10ByOrderByCultureCountDesc();
                    for(KeywordEntity keywordEntity : top10ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<KeywordEntity> top10ByOrderByInternationalCountDesc = keywordRepository.findTop10ByOrderByInternationalCountDesc();
                    for (KeywordEntity keywordEntity : top10ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<KeywordEntity> top10ByOrderByLocalCountDesc = keywordRepository.findTop10ByOrderByLocalCountDesc();
                    for (KeywordEntity keywordEntity : top10ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<KeywordEntity> top10ByOrderBySportsCountDesc = keywordRepository.findTop10ByOrderBySportsCountDesc();
                    for (KeywordEntity keywordEntity : top10ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<KeywordEntity> top10ByOrderByItScienceCountDesc = keywordRepository.findTop10ByOrderByItScienceCountDesc();
                    for (KeywordEntity keywordEntity : top10ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                }
            } else if (count == 1) {
                if(value == "PoliticsScore") {
                    List<KeywordEntity> top5ByOrderByPoliticsCountDesc = keywordRepository.findTop5ByOrderByPoliticsCountDesc();
                    for(KeywordEntity keywordEntity : top5ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<KeywordEntity> top5ByOrderByEconomyCountDesc = keywordRepository.findTop5ByOrderByEconomyCountDesc();
                    for(KeywordEntity keywordEntity : top5ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<KeywordEntity> top5ByOrderBySocietyCountDesc = keywordRepository.findTop5ByOrderBySocietyCountDesc();
                    for(KeywordEntity keywordEntity : top5ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<KeywordEntity> top5ByOrderByCultureCountDesc = keywordRepository.findTop5ByOrderByCultureCountDesc();
                    for(KeywordEntity keywordEntity : top5ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<KeywordEntity> top5ByOrderByInternationalCountDesc = keywordRepository.findTop5ByOrderByInternationalCountDesc();
                    for (KeywordEntity keywordEntity : top5ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<KeywordEntity> top5ByOrderByLocalCountDesc = keywordRepository.findTop5ByOrderByLocalCountDesc();
                    for (KeywordEntity keywordEntity : top5ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<KeywordEntity> top5ByOrderBySportsCountDesc = keywordRepository.findTop5ByOrderBySportsCountDesc();
                    for (KeywordEntity keywordEntity : top5ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<KeywordEntity> top5ByOrderByItScienceCountDesc = keywordRepository.findTop5ByOrderByItScienceCountDesc();
                    for (KeywordEntity keywordEntity : top5ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                }
            } else if (count == 2) {
                if(value == "PoliticsScore") {
                    List<KeywordEntity> top3ByOrderByPoliticsCountDesc = keywordRepository.findTop3ByOrderByPoliticsCountDesc();
                    for(KeywordEntity keywordEntity : top3ByOrderByPoliticsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "EconomyScore") {
                    List<KeywordEntity> top3ByOrderByEconomyCountDesc = keywordRepository.findTop3ByOrderByEconomyCountDesc();
                    for(KeywordEntity keywordEntity : top3ByOrderByEconomyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SocietyScore") {
                    List<KeywordEntity> top3ByOrderBySocietyCountDesc = keywordRepository.findTop3ByOrderBySocietyCountDesc();
                    for(KeywordEntity keywordEntity : top3ByOrderBySocietyCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "CultureScore") {
                    List<KeywordEntity> top3ByOrderByCultureCountDesc = keywordRepository.findTop3ByOrderByCultureCountDesc();
                    for(KeywordEntity keywordEntity : top3ByOrderByCultureCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "InternationalScore") {
                    List<KeywordEntity> top3ByOrderByInternationalCountDesc = keywordRepository.findTop3ByOrderByInternationalCountDesc();
                    for (KeywordEntity keywordEntity : top3ByOrderByInternationalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "LocalScore") {
                    List<KeywordEntity> top3ByOrderByLocalCountDesc = keywordRepository.findTop3ByOrderByLocalCountDesc();
                    for (KeywordEntity keywordEntity : top3ByOrderByLocalCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "SportsScore") {
                    List<KeywordEntity> top3ByOrderBySportsCountDesc = keywordRepository.findTop3ByOrderBySportsCountDesc();
                    for (KeywordEntity keywordEntity : top3ByOrderBySportsCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
                            }
                        }
                    }
                } else if (value == "ItScienceScore") {
                    List<KeywordEntity> top3ByOrderByItScienceCountDesc = keywordRepository.findTop3ByOrderByItScienceCountDesc();
                    for (KeywordEntity keywordEntity : top3ByOrderByItScienceCountDesc) {
                        Boolean check = true;
                        for(UserKeywordEntity userKeywordEntity : userKeywordEntities) {
                            if(userKeywordEntity.getKeywordEntity() == keywordEntity) {
                                check = false;
                                break;
                            }
                        }
                        if(check) {
                            if(!keywordNames.contains(keywordEntity.getKeywordName())) {
                                keywordNames.add(keywordEntity.getKeywordName());
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
*/
