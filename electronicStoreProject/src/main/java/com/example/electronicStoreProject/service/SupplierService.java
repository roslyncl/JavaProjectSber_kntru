package com.example.electronicStoreProject.service;

import com.example.electronicStoreProject.entity.Supplier;
import com.example.electronicStoreProject.exception.ResourceNotFoundException;
import com.example.electronicStoreProject.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    public SupplierService(SupplierRepository supplierRepository){ this.supplierRepository = supplierRepository; }

    public List<Supplier> getAllSuppliers() {
        log.info("Запрос на получение всех поставщиков");
        List<Supplier> suppliers = supplierRepository.findAll();
        log.info("Успешно получено {} поставщиков", suppliers.size());
        return suppliers;
    }

    public Supplier getSupplierById(Long id) {
        log.info("Получение поставщика по ID: {}", id);
        return supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Поставщик с ID {} не найден", id);
                    return new ResourceNotFoundException("Поставщик c id " + id + " не найден");
                });
    }

    public Supplier addSupplier(Supplier supplier) {
        log.info("Попытка добавить нового поставщика: {}", supplier);
        Supplier savedSupplier = supplierRepository.save(supplier);
        log.info("Успешно добавлен новый поставщик с ID: {}", savedSupplier.getId());
        return savedSupplier;
    }

    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        log.info("Попытка обновления поставщика с ID: {}", id);
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Поставщик с ID {} не найден", id);
                    return new ResourceNotFoundException("Поставщик c id " + id + " не найден");
                });
        log.info("Обновление полей поставщика с ID: {}", id);
        supplier.setName(updatedSupplier.getName());
        supplier.setAddress(updatedSupplier.getAddress());
        supplier.setPhoneNumber(updatedSupplier.getPhoneNumber());
        Supplier savedSupplier = supplierRepository.save(supplier);
        log.info("Успешно обновлен поставщик с ID: {}", id);
        return savedSupplier;
    }

    public void deleteSupplier(Long id) {
        log.info("Попытка удаления поставщика с ID: {}", id);
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Поставщик с ID {} не найден для удаления", id);
                    return new ResourceNotFoundException("Поставщик с id " + id + " не найден");
                });
        supplierRepository.delete(supplier);
        log.info("Успешно удален поставщик с ID: {}", id);
    }
}
