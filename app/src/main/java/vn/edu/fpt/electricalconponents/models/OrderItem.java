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
public class OrderItem implements Serializable {
    private String id;
    private String orderId;
    private String productId;
    private int quantity;
    private int price;
}
