package com.cavemen.dbscw.entities.customer;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
    @Query("OPTIONAL MATCH(c:Customer), (cat:Category) " +
            "WHERE id(c) = $customerID AND id(cat) = $categoryID " +
            "CREATE (c)-[r:PREFERS]->(cat)")
    void connect(@Param("customerID") Long customerID, @Param("categoryID") Long categoryID);
}
