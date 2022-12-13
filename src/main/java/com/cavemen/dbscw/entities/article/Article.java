package com.cavemen.dbscw.entities.article;

import com.cavemen.dbscw.entities.readyItem.ReadyItem;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(
            generator = "article_uuid"
    )
    @GenericGenerator(
            name = "article_uuid",
            strategy = "uuid"
    )
    @Column(name = "article_id")
    private String id;
    private String nameOfProductUA;
    private String nameOfProductEN;
    private String nameOfSupplier;
    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ReadyItem readyItem;
    public Article(String nameOfProductUA, String nameOfProductEN, String nameOfSupplier) {
        this.nameOfProductUA = nameOfProductUA;
        this.nameOfProductEN = nameOfProductEN;
        this.nameOfSupplier = nameOfSupplier;
    }

    public Article() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfProductUA() {
        return nameOfProductUA;
    }

    public void setNameOfProductUA(String nameOfProductUA) {
        this.nameOfProductUA = nameOfProductUA;
    }

    public String getNameOfProductEN() {
        return nameOfProductEN;
    }

    public void setNameOfProductEN(String nameOfProductEN) {
        this.nameOfProductEN = nameOfProductEN;
    }

    public String getNameOfSupplier() {
        return nameOfSupplier;
    }

    public void setNameOfSupplier(String nameOfSupplier) {
        this.nameOfSupplier = nameOfSupplier;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", nameOfProductUA='" + nameOfProductUA + '\'' +
                ", nameOfProductEN='" + nameOfProductEN + '\'' +
                ", nameOfSupplier='" + nameOfSupplier + '\'' +
                '}';
    }
}
