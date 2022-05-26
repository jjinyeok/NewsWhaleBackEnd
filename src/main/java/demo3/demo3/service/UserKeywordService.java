package demo3.demo3.service;

import demo3.demo3.dto.DeleteKeywordDto;
import demo3.demo3.dto.GetKeywordsDto;
import demo3.demo3.dto.PostKeywordDto;
import demo3.demo3.entity.Keyword;
import demo3.demo3.entity.User;
import demo3.demo3.entity.UserKeyword;
import demo3.demo3.repository.KeywordRepository;
import demo3.demo3.repository.UserKeywordRepository;
import demo3.demo3.repository.UserRepository;
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
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(userId));
        User user = new User();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 조회할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }

        // 2. user로부터 UserKeyword 리스트 (사용자가 등록한 Keyword) find
        List<UserKeyword> userKeywords = userKeywordRepository.findAllByUser(user);

        // 3. UserKeyword 리스트로부터 등록된 키워드 이름 find
        List<String> userKeywordNames = new ArrayList<>();
        for (UserKeyword userKeyword : userKeywords) {
            userKeywordNames.add(userKeyword.getKeyword().getKeywordName());
        }

        // 4. GetKeywordsDto return
        GetKeywordsDto getKeywordsDto = GetKeywordsDto.builder()
                .count(Long.valueOf(userKeywords.size()))
                .keywordName(userKeywordNames)
                .build();

        return getKeywordsDto;
    }

    // 키워드 추가하기 서비스
    public PostKeywordDto postKeyword(PostKeywordDto postKeywordsDto) {

        // 1. userId로부터 User find
        Optional<User> optionalUser = userRepository.findById(postKeywordsDto.getUserId());
        User user = new User();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 추가할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }

        // 2. 키워드 중복 확인
        Optional<Keyword> optionalKeyword = keywordRepository.findByKeywordName(postKeywordsDto.getKeywordName());
        Keyword keyword = new Keyword();
        // 2-1. 중복이 되어있지 않다면, 키워드를 새로 생성 후 KeywordRepository에 save
        if(optionalKeyword.isEmpty()) {
            keyword = Keyword.builder()
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
            keywordRepository.save(keyword);
        }
        // 2-2. 중복이 되어있다면 기존 키워드 find
        else {
            keyword = optionalKeyword.get();
            user.setPoliticsScore(user.getPoliticsScore() + keyword.getPoliticsCount());
            user.setEconomyScore(user.getEconomyScore() + keyword.getEconomyCount());
            user.setSocietyScore(user.getSocietyScore() + keyword.getSocietyCount());
            user.setCultureScore(user.getCultureScore() + keyword.getCultureCount());
            user.setInternationalScore(user.getInternationalScore() + keyword.getInternationalCount());
            user.setLocalScore(user.getLocalScore() + keyword.getLocalCount());
            user.setSportsScore(user.getSportsScore() + keyword.getSportsCount());
            user.setItScienceScore(user.getItScienceScore() + keyword.getItScienceCount());
        }

        // 3. UserKeyword 중복 확인
        Optional<UserKeyword> optionalUserKeyword = userKeywordRepository.findByUserAndKeyword(user, keyword);
        if(optionalUserKeyword.isEmpty()) {
            // 3-1. userId와 keywordName 기반으로 userKeyword 엔티티 생성
            UserKeyword userKeyword = UserKeyword.builder()
                    .user(user)
                    .keyword(keyword)
                    .build();

            // 3-2. UserKeywordRepository에 save
            userKeywordRepository.save(userKeyword);
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
        User user = new User();
        Keyword keyword = new Keyword();
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("사용자가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            user = optionalUser.get();
        }
        if(optionalKeyword.isEmpty()) {
            throw new RuntimeException("키워드가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            keyword = optionalKeyword.get();
        }

        // 2. User와 Keyword로부터 UserKeyword find
        var optionalUserKeyword = userKeywordRepository.findByUserAndKeyword(user, keyword);
        UserKeyword userKeyword = new UserKeyword();
        if(optionalUserKeyword.isEmpty()) {
            throw new RuntimeException("사용자 등록 키워드가 없습니다. 키워드를 삭제할 수 없습니다.");
        } else {
            userKeyword = optionalUserKeyword.get();
        }

        // 3. UserKeywordRepository로부터 delete
        userKeywordRepository.delete(userKeyword);

        // 4. DeleteKeywordDto return
        DeleteKeywordDto deleteKeywordDto = DeleteKeywordDto.builder()
                .userId(Long.parseLong(userId))
                .keywordName(keywordName)
                .build();

        return deleteKeywordDto;
    }

}
