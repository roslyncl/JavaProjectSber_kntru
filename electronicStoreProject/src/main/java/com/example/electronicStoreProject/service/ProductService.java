package com.example.electronicStoreProject.service;

import com.example.electronicStoreProject.entity.Product;
import com.example.electronicStoreProject.exception.ResourceNotFoundException;
import com.example.electronicStoreProject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        log.info("Запрос на получение всех товаров");
        List<Product> products = productRepository.findAll();
        log.info("Успешно получено {} товаров", products.size());
        return products;
    }

    public Product getProductById(Long id) {
        log.info("Получение товара по ID: {}", id);
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Товар с ID {} не найден", id);
                    return new ResourceNotFoundException("Товар c id " + id + " не найден");
                });
    }

    public Product addProduct(Product product) {
        log.info("Попытка добавить новый товар: {}", product);
        Product savedProduct = productRepository.save(product);
        log.info("Успешно добавлен новый товар с ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        log.info("Попытка обновления товара с ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Товар с ID {} не найден", id);
                    return new ResourceNotFoundException("Товар c id " + id + " не найден");
                });
        log.info("Обновление полей товара с ID: {}", id);
        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setAvailableStock(updatedProduct.getAvailableStock());
        product.setLastUpdateDate(updatedProduct.getLastUpdateDate());
        product.setSupplier(updatedProduct.getSupplier());
        Product savedProduct = productRepository.save(product);
        log.info("Успешно обновлен товар с ID: {}", id);
        return savedProduct;
    }

    public void deleteProduct(Long id) {
        log.info("Попытка удаления товара с ID: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Товар с ID {} не найден для удаления", id);
                    return new ResourceNotFoundException("Товар с id " + id + " не найден");
                });
        productRepository.delete(product);
        log.info("Успешно удален товар с ID: {}", id);
    }

    public List<Product> getProductsBySupplierName(String supplierName) {
        log.info("Поиск товаров по имени поставщика: '{}'", supplierName);
        List<Product> products = productRepository.findBySupplierNameContaining(supplierName);
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Товары, принадлежащие поставщику " + supplierName + ", не найдены");
        }
        log.info("Найдено {} товаров для '{}'", products.size(), supplierName);
        return products;
    }
}