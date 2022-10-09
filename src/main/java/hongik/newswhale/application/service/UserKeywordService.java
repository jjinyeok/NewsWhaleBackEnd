/*
package hongik.newswhale.application.service;

import hongik.newswhale.dto.DeleteKeywordDto;
import hongik.newswhale.dto.GetKeywordsDto;
import hongik.newswhale.dto.PostKeywordDto;
import hongik.newswhale.infrastructure.persistance.jpa.entity.KeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserEntity;
import hongik.newswhale.infrastructure.persistance.jpa.entity.UserKeywordEntity;
import hongik.newswhale.infrastructure.persistance.jpa.repository.KeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserKeywordRepository;
import hongik.newswhale.infrastructure.persistance.jpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserKeywordService {

    private final UserRepository userRepository;
    private final UserKeywordRepository userKeywordRepository;
    private final KeywordRepository keywordRepository;

    public UserKeywordService (UserRepository userRepository,
                               UserKeywordRepository userKeywordRepository,
                               KeywordRepository keywordRepository) {
        this.userRepository = userRepository;
        this.userKeywordRepository = userKeywordRepository;
        this.keywordRepository = keywordRepository;
    }

    // 등록된 키워드 조회 서비스
    public GetKeywordsDto getKeywords(String userId) {

        // 1. userId로부터 User find
        Optional<UserEntity> optionalUser = userRepository.findById(Long.parseLong(userId));
        UserEntity userEntity = new UserEntity();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }

        // 2. user로부터 UserKeyword 리스트 (사용자가 등록한 Keyword) find
        List<UserKeywordEntity> userKeywordEntities = userKeywordRepository.findAllByUser(userEntity);

        // 3. UserKeyword 리스트로부터 등록된 키워드 이름 find
        List<String> userKeywordNames = new ArrayList<>();
        for (UserKeywordEntity userKeywordEntity : userKeywordEntities) {
            userKeywordNames.add(userKeywordEntity.getKeywordEntity().getKeywordName());
        }

        // 4. GetKeywordsDto return
        GetKeywordsDto getKeywordsDto = GetKeywordsDto.builder()
                .count(Long.valueOf(userKeywordEntities.size()))
                .keywordName(userKeywordNames)
                .build();

        return getKeywordsDto;
    }

    // 키워드 추가하기 서비스
    public PostKeywordDto postKeyword(PostKeywordDto postKeywordsDto) {

        // 1. userId로부터 User find
        Optional<UserEntity> optionalUser = userRepository.findById(postKeywordsDto.getUserId());
        UserEntity userEntity = new UserEntity();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 추가할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }

        // 2. 키워드 중복 확인
        Optional<KeywordEntity> optionalKeyword = keywordRepository.findByKeywordName(postKeywordsDto.getKeywordName());
        KeywordEntity keywordEntity = new KeywordEntity();
        // 2-1. 중복이 되어있지 않다면, 키워드를 새로 생성 후 KeywordRepository에 save
        if(optionalKeyword.isEmpty()) {
            keywordEntity = KeywordEntity.builder()
                    .keywordName(postKeywordsDto.getKeywordName())
                    .politicsCount(0L)
                    .economyCount(0L)
                    .societyCount(0L)
                    .cultureCount(0L)
                    .internationalCount(0L)
                    .localCount(0L)
                    .sportsCount(0L)
                    .itScienceCount(0L)
                    .build();
            keywordRepository.save(keywordEntity);
        }
        // 2-2. 중복이 되어있다면 기존 키워드 find
        else {
            keywordEntity = optionalKeyword.get();
            userEntity.setPoliticsScore(userEntity.getPoliticsScore() + keywordEntity.getPoliticsCount());
            userEntity.setEconomyScore(userEntity.getEconomyScore() + keywordEntity.getEconomyCount());
            userEntity.setSocietyScore(userEntity.getSocietyScore() + keywordEntity.getSocietyCount());
            userEntity.setCultureScore(userEntity.getCultureScore() + keywordEntity.getCultureCount());
            userEntity.setInternationalScore(userEntity.getInternationalScore() + keywordEntity.getInternationalCount());
            userEntity.setLocalScore(userEntity.getLocalScore() + keywordEntity.getLocalCount());
            userEntity.setSportsScore(userEntity.getSportsScore() + keywordEntity.getSportsCount());
            userEntity.setItScienceScore(userEntity.getItScienceScore() + keywordEntity.getItScienceCount());
        }

        // 3. UserKeyword 중복 확인
        Optional<UserKeywordEntity> optionalUserKeyword = userKeywordRepository.findByUserAndKeyword(userEntity, keywordEntity);
        if(optionalUserKeyword.isEmpty()) {
            // 3-1. userId와 keywordName 기반으로 userKeyword 엔티티 생성
            UserKeywordEntity userKeywordEntity = UserKeywordEntity.builder()
                    .userEntity(userEntity)
                    .keywordEntity(keywordEntity)
                    .build();

            // 3-2. UserKeywordRepository에 save
            userKeywordRepository.save(userKeywordEntity);
            postKeywordsDto.setSuccess(true);
        } else {
            postKeywordsDto.setSuccess(false);
        }

        return postKeywordsDto;
    }

    // 키워드 삭제하기 서비스
    public DeleteKeywordDto deleteKeyword(String userId, String keywordName) {

        // 1. userId로부터 User find, keywordName으로부터 Keyword find
        var optionalUser = userRepository.findById(Long.parseLong(userId));
        var optionalKeyword = keywordRepository.findByKeywordName(keywordName);
        UserEntity userEntity = new UserEntity();
        KeywordEntity keywordEntity = new KeywordEntity();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            userEntity = optionalUser.get();
        }
        if(optionalKeyword.isEmpty()) {
            throw new RuntimeException("키워드가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            keywordEntity = optionalKeyword.get();
        }

        // 2. User와 Keyword로부터 UserKeyword find
        var optionalUserKeyword = userKeywordRepository.findByUserAndKeyword(userEntity, keywordEntity);
        UserKeywordEntity userKeywordEntity = new UserKeywordEntity();
        if(optionalUserKeyword.isEmpty()) {
            throw new RuntimeException("사용자 등록 키워드가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            userKeywordEntity = optionalUserKeyword.get();
        }

        // 3. UserKeywordRepository로부터 delete
        userKeywordRepository.delete(userKeywordEntity);

        // 4. DeleteKeywordDto return
        DeleteKeywordDto deleteKeywordDto = DeleteKeywordDto.builder()
                .userId(Long.parseLong(userId))
                .keywordName(keywordName)
                .build();

        return deleteKeywordDto;
    }

}
*/
