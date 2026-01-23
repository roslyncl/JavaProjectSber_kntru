package com.example.electronicStoreProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.electronicStoreProject.entity.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c JOIN FETCH c.address a WHERE a.city = :city")
    List<Client> findClientsByCity(@Param("city") String city);
}
