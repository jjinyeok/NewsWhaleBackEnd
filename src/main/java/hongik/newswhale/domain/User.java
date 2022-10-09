package hongik.newswhale.domain;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.util.Set;


@Getter
@Builder
@ToString
public class User {

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Long politicsScore;
    private Long economyScore;
    private Long societyScore;
    private Long cultureScore;
    private Long internationalScore;
    private Long localScore;
    private Long sportsScore;
    private Long itScienceScore;
    private boolean activated;
    private Set<Authority> authorities;
}
