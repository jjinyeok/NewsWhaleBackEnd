package hongik.newswhale.ui.requestbody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserDuplicateCheckRequest {
    private final Long userId;
}
