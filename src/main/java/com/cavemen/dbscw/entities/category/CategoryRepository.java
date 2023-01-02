package com.cavemen.dbscw.entities.category;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends Neo4jRepository<Category, Long> {

}
