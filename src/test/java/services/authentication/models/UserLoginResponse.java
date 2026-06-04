package services.authentication.models;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserLoginResponse {
    @JsonProperty("token")
    private String accessToken;

    @JsonProperty("user")
    private User user;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        private String _id;
        private String firstName;
        private String lastName;
        private String email;
    }

}
