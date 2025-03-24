package vn.edu.fpt.electricalconponents.apis.user.dto;

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
public class UpdateUserRequest implements Serializable {
    private String fullname;
    private String phoneNumber;
    private String avatar;
}
