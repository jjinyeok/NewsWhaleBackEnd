package hongik.newswhale.application.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Authority {
    private String authorityName;
}
