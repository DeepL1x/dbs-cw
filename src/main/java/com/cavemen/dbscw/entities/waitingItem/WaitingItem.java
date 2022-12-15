package com.cavemen.dbscw.entities.waitingItem;

import com.cavemen.dbscw.entities.article.Article;
import jakarta.persistence.*;

@Entity
@Table(name = "waitingItem")
public class WaitingItem {
    @Id
    private String id;
    private Long totalItemAmount;
    private Double price;
    private String measureUnit;
    @OneToOne
    @MapsId
    private Article article;

    public WaitingItem() {
    }

    public WaitingItem(Long totalItemAmount, Double price, String measureUnit, Article article) {
        this.totalItemAmount = totalItemAmount;
        this.price = price;
        this.measureUnit = measureUnit;
        this.article = article;
    }

    public WaitingItem(String id, Long totalItemAmount, Double price, String measureUnit, Article article) {
        this.id = id;
        this.totalItemAmount = totalItemAmount;
        this.price = price;
        this.measureUnit = measureUnit;
        this.article = article;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTotalItemAmount() {
        return totalItemAmount;
    }

    public void setTotalItemAmount(Long totalItemAmount) {
        this.totalItemAmount = totalItemAmount;
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

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

//    public Article getArticle() {
//        return article;
//    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "WaitingItem{" +
                "id='" + id + '\'' +
                ", totalItemAmount=" + totalItemAmount +
                ", price=" + price +
                ", measureUnit='" + measureUnit +
                '}';
    }
}

