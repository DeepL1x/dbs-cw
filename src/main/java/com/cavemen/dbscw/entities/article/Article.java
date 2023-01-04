package com.cavemen.dbscw.entities.article;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import com.cavemen.dbscw.entities.waitingItem.WaitingItem;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
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
    @Temporal(TemporalType.DATE)
    private Date expirationTerm;
    @Transient
    private boolean disposalNeeded;

    @OneToOne(mappedBy = "article", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private ReadyItem readyItem;

    @OneToOne(mappedBy = "article", cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private WaitingItem waitingItem;

    public Article() {

    }

    public Article(String nameOfProductUA, String nameOfProductEN, String nameOfSupplier, Date expirationTerm) {
        this.productNameUA = nameOfProductUA;
        this.productNameEN = nameOfProductEN;
        this.supplierName = nameOfSupplier;
        this.expirationTerm = expirationTerm;
        validateExpTerm();
    }

    public Article(String id, String nameOfProductUA, String nameOfProductEN, String nameOfSupplier, Date expirationTerm) {
        this.id = id;
        this.productNameUA = nameOfProductUA;
        this.productNameEN = nameOfProductEN;
        this.supplierName = nameOfSupplier;
        this.expirationTerm = expirationTerm;
        validateExpTerm();
    }

    public boolean isDisposalNeeded() {
        return disposalNeeded;
    }

    public Date getExpirationTerm() {
        return expirationTerm;
    }

    public void setExpirationTerm(java.sql.Date expirationTerm) {
        this.expirationTerm = expirationTerm;
        validateExpTerm();
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

    @PostLoad
    public void validateExpTerm(){
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        this.disposalNeeded = currentDate.after(this.expirationTerm);
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
