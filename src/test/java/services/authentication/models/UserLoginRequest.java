package services.authentication.models;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginRequest {
    private String email;
    private String password;

}
