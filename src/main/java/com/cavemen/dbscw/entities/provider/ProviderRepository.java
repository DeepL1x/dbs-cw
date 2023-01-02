package com.cavemen.dbscw.entities.provider;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public interface ProviderRepository extends Neo4jRepository<Provider, Long> {
    @Query("OPTIONAL MATCH(p:Provider), (cat:Category) " +
            "WHERE id(p) = $providerID AND id(cat) = $categoryID " +
            "CREATE (p)-[r:PROVIDE]->(cat)")
    void connect(@Param("providerID") Long providerID, @Param("categoryID") Long categoryID);

    @Query("MATCH (p:Provider) - [:PROVIDE] -> (cat:Category) " +
            "WHERE id(p) = $providerID " +
            "RETURN cat.title")
    HashSet<String> getCategories(@Param("providerID") Long providerID);
}
