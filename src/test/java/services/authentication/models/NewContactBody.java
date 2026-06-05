package services.authentication.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NewContactBody {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String email;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
