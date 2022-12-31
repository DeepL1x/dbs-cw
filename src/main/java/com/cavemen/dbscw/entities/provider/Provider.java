package com.cavemen.dbscw.entities.provider;

import com.cavemen.dbscw.entities.category.Category;
import org.springframework.data.neo4j.core.schema.*;

import java.util.HashSet;
import java.util.Set;

@Node("Provider")
public class Provider {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String country;

    @Relationship(type = "PROVIDE", direction = Relationship.Direction.OUTGOING)
    private Set<Category> categorySet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public Provider() {}

    public Provider(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public void addCategory(Category category) {
        categorySet.add(category);
    }
}
