package com.blps.demo.repository;

import com.blps.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query(value = "SELECT * FROM product p WHERE (cast(:name as varchar) is null or p.name like cast(:name as varchar)) " +
            "and (cast(:category as varchar) is null or p.category like cast(:category as varchar)) " +
            "and (cast(:minPrice as integer) is null or cast(:minPrice as integer) <= p.price) " +
            "and (cast(:maxPrice as integer) is null or cast(:maxPrice as integer) >= p.price) " +
            "and (cast(:minSize as integer) is null or cast(:minSize as integer) <= p.size) " +
            "and (cast(:maxSize as integer) is null or cast(:maxSize as integer) >= p.size) " +
            "and p.count > 0", nativeQuery = true)
    public List<Product> filter(@Param("name") String name,
                                @Param("category") String category,
                                @Param("minPrice") Integer minPrice,
                                @Param("maxPrice") Integer maxPrice,
                                @Param("minSize") Integer minSize,
                                @Param("maxSize") Integer maxSize);
}
