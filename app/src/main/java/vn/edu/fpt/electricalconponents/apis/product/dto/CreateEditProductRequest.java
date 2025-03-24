package vn.edu.fpt.electricalconponents.apis.product.dto;

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
public class CreateEditProductRequest implements Serializable {
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;
    private String categoryId;
    private String brandId;
}
