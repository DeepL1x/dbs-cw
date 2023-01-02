package com.cavemen.dbscw.entities.category;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("Category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Relationship(type = "PREFERS", direction = Relationship.Direction.INCOMING)
    private Set<Category> customerSet = new HashSet<>();

    @Relationship(type = "PROVIDE", direction = Relationship.Direction.INCOMING)
    private Set<Category> providerSet = new HashSet<>();;

    public Category() {}

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Category> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Category> customerSet) {
        this.customerSet = customerSet;
    }

    public Set<Category> getProviderSet() {
        return providerSet;
    }

    public void setProviderSet(Set<Category> providerSet) {
        this.providerSet = providerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id) && Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
