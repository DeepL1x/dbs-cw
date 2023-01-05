package com.cavemen.dbscw.entities.customer;

import com.cavemen.dbscw.entities.category.Category;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("Customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String name;

    private String country;

    private String address;

    @Relationship(type = "PREFERS", direction = Relationship.Direction.OUTGOING)
    private Set<Category> categorySet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public Customer() {}

    public Customer(Long id, String login, String name, String country, String address) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.country = country;
        this.address = address;
    }

    public void addCategory(Category category) {
        categorySet.add(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && Objects.equals(login, customer.login) && Objects.equals(name, customer.name) && Objects.equals(country, customer.country) && Objects.equals(categorySet, customer.categorySet) && Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
