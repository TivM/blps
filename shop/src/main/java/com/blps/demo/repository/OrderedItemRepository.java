package com.blps.demo.repository;

import com.blps.demo.entity.OrderedItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {

    @Modifying()
    @Query(value = "update ordered_item set status = :status where id = :id", nativeQuery = true)
    void setStatusById(@Param("id") int id, @Param("status") String status);

    List<OrderedItem> findByProductOrderId(int productOrderId);
}
