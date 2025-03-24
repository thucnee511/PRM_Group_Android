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
public class Product implements Serializable {
    private String id;
    private String categoryId;
    private String brandId;
    private String name;
    private String description;
    private int price;
    private int stock;
    private String imageUrl;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime craetedAt;
    private LocalDateTime updatedAt;
}
