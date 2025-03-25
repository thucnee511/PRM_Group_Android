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
public class CartItem implements Serializable {
    private String id;
    private String cartId;
    private String productId;
    private int quantity;
    private int price;
    private String createdAt;
    private String updatedAt;
}
