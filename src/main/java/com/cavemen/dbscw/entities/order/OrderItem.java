package com.cavemen.dbscw.entities.order;

import com.cavemen.dbscw.entities.article.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderItem {
    private String id;
    private Long value;
    @MapsId
    @OneToOne
    @JsonIgnore
    private Article article;

    public OrderItem() {
    }

    public OrderItem(String id, Long value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getValue() {
        return value;
    }

//    public Article getArticle() {
//        return article;
//    }
    @JsonIgnore
    public void setArticle(Article article) {
        this.article = article;
    }

    public void setValue(Long  value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CustomKeyValue{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", article=" + article +
                '}';
    }
}
