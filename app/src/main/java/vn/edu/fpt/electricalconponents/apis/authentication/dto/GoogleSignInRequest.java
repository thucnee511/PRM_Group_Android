package vn.edu.fpt.electricalconponents.apis.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GoogleSignInRequest {
    private String email;
    private String username;
    private String phoneNumber;
}
