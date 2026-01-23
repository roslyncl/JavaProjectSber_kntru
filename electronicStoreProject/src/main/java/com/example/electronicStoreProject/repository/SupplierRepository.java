package com.example.electronicStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicStoreProject.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}