package com.example.electronicStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicStoreProject.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
}
