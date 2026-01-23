package com.example.electronicStoreProject.controller;

import com.example.electronicStoreProject.entity.Supplier;
import com.example.electronicStoreProject.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService supplierService;
    public SupplierController(SupplierService supplierService){ this.supplierService = supplierService; }
    @GetMapping("/")
    public List<Supplier> getAllSuppliers() { return supplierService.getAllSuppliers();}

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Long id) {return supplierService.getSupplierById(id);}

    @PostMapping
    public Supplier addSupplier(@Valid @RequestBody Supplier supplier) { return supplierService.addSupplier(supplier);}

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id,@Valid @RequestBody Supplier supplier) { return supplierService.updateSupplier(id, supplier);}

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) { supplierService.deleteSupplier(id);}
}
