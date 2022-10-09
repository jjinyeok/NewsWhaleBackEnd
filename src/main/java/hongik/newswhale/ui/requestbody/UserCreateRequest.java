package hongik.newswhale.ui.requestbody;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserCreateRequest {
    private final String username;
    private final String password;
    private final String nickname;
    private final String email;
}
