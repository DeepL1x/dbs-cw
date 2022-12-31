package com.cavemen.dbscw.entities.worker;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends Neo4jRepository<Worker, Long> {
    @Query("MATCH (w:Worker) " +
            "WHERE w.login = $login " +
            "RETURN w")
    public Worker findByLogin(@Param("login") String login);
}
