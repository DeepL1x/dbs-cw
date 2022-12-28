package com.cavemen.dbscw.entities.article;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GenericGenerator(
            name = "article_id",
            strategy = "com.cavemen.dbscw.entities.article.ArticleIdGenerator"
    )
    @GeneratedValue(generator = "article_id")
    @Column(length = 8)
    private String id;
    @Column(name = "ua_product_name")
    private String productNameUA;
    @Column(name = "en_product_name")
    private String productNameEN;
    private String supplierName;
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private com.cavemen.dbscw.entities.readyItem.ReadyItem readyItem;

    public Article() {

    }

    public Article(String nameOfProductUA, String nameOfProductEN, String nameOfSupplier) {
        this.productNameUA = nameOfProductUA;
        this.productNameEN = nameOfProductEN;
        this.supplierName = nameOfSupplier;
    }

    public Article(String id, String nameOfProductUA, String nameOfProductEN, String nameOfSupplier) {
        this.id = id;
        this.productNameUA = nameOfProductUA;
        this.productNameEN = nameOfProductEN;
        this.supplierName = nameOfSupplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNameUA() {
        return productNameUA;
    }

    public void setProductNameUA(String nameOfProductUA) {
        this.productNameUA = nameOfProductUA;
    }

    public String getProductNameEN() {
        return productNameEN;
    }

    public void setProductNameEN(String nameOfProductEN) {
        this.productNameEN = nameOfProductEN;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String nameOfSupplier) {
        this.supplierName = nameOfSupplier;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", productNameUA='" + productNameUA + '\'' +
                ", productNameEN='" + productNameEN + '\'' +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }
}
