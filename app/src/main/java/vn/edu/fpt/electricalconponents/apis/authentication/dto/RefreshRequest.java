package vn.edu.fpt.electricalconponents.apis.authentication.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RefreshRequest implements Serializable {

    private String refreshToken;
}
