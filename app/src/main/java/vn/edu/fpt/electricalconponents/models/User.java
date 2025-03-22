package vn.edu.fpt.electricalconponents.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class User implements Serializable {
    private String id;
    private String fullname;
    private String email;
    private String role;
    private String phoneNumber;
    private String avatar;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
