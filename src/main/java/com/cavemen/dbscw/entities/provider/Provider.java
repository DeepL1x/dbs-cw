package com.cavemen.dbscw.entities.provider;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;

@Node("Provider")
public class Provider {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String country;

    public Provider() {}

    public Provider(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
