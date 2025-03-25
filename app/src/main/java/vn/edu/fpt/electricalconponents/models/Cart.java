package vn.edu.fpt.electricalconponents.models;

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
public class Cart implements Serializable {
    private String id;
    private String userId;
    private int totalItems;
    private int totalValue;
    private String createdAt;
    private String updatedAt;
}
