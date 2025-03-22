package vn.edu.fpt.electricalconponents.models;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    public Product() {
    }

    public Product(String id
            , String categoryId
            , String brandId
            , String name
            , String description
            , int price
            , int stock
            , String imageUrl
            , boolean isActive
            , boolean isDeleted
            , LocalDateTime craetedAt
            , LocalDateTime updatedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.craetedAt = craetedAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCraetedAt() {
        return craetedAt;
    }

    public void setCraetedAt(LocalDateTime craetedAt) {
        this.craetedAt = craetedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", brandId='" + brandId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                ", craetedAt=" + craetedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
