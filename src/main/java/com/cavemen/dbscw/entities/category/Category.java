package com.cavemen.dbscw.entities.category;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    public Category() {}

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
