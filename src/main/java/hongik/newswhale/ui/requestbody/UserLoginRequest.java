package hongik.newswhale.ui.requestbody;


import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class UserLoginRequest {
    private final String username;
    private final String password;
}
