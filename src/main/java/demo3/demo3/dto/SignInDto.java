package demo3.demo3.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInDto {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
