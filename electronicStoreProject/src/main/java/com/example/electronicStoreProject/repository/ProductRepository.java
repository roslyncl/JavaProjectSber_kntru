package com.example.electronicStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicStoreProject.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.supplier s WHERE s.name = :supplierName")
    List<Product> findBySupplierNameContaining(@Param("supplierName") String supplierName);
}