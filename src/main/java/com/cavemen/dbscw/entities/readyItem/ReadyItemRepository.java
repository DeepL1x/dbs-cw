package com.cavemen.dbscw.entities.readyItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReadyItemRepository extends JpaRepository<ReadyItem, String> {
    @Modifying
    @Query("DELETE FROM ReadyItem r WHERE r.id = ?1")
    void deleteById(String s);
}
