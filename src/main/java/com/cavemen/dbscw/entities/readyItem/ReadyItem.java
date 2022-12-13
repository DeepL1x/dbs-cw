package com.cavemen.dbscw.entities.readyItem;

import com.cavemen.dbscw.entities.article.Article;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "ready_item")
public class ReadyItem {
    @Id
    @Column (name = "ready_item_id")
    private String id;
    private Long totalItemAmount;
    private Long reservedItemAmount;
    @Formula("total_item_amount - reserved_item_amount")
    private Long availableItemAmount;
    private String storagePlace;
    private Double price;
    private String measureUnit;
    @OneToOne
    @MapsId
    @JoinColumn(name = "ready_item_id")
    private Article article;

    public ReadyItem() {
    }

    public ReadyItem(Article id, Long totalItemAmount, Long reservedItemAmount, Long availableItemAmount, String storagePlace, Double price, String unitOfMeasure) {
        this.id = id.getId();
        this.totalItemAmount = totalItemAmount;
        this.reservedItemAmount = reservedItemAmount;
        this.availableItemAmount = availableItemAmount;
        this.storagePlace = storagePlace;
        this.price = price;
        this.measureUnit = unitOfMeasure;
    }

    public String getId() {
        return id;
    }

    public void setId(Article id) {
        this.id = id.getId();
    }

    public Long getTotalItemAmount() {
        return totalItemAmount;
    }

    public void setTotalItemAmount(Long totalItemAmount) {
        this.totalItemAmount = totalItemAmount;
    }

    public Long getReservedItemAmount() {
        return reservedItemAmount;
    }

    public void setReservedItemAmount(Long reservedItemAmount) {
        this.reservedItemAmount = reservedItemAmount;
    }

    public Long getAvailableItemAmount() {
        return availableItemAmount;
    }

    public void setAvailableItemAmount(Long availableItemAmount) {
        this.availableItemAmount = availableItemAmount;
    }

    public String getStoragePlace() {
        return storagePlace;
    }

    public void setStoragePlace(String storagePlace) {
        this.storagePlace = storagePlace;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String integer) {
        this.measureUnit = integer;
    }

    @Override
    public String toString() {
        return "ReadyItem{" +
                "id=" + id +
                ", totalItemAmount=" + totalItemAmount +
                ", reservedItemAmount=" + reservedItemAmount +
                ", availableItemAmount=" + availableItemAmount +
                ", storagePlace='" + storagePlace + '\'' +
                ", price=" + price +
                ", measureUnit='" + measureUnit + '\'' +
                '}';
    }
}
