package com.example.electronicStoreProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@Check(constraints = "price > 0")
@Check(constraints = "available_stock >= 0")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название товара обязательно")
    @Size(max = 255, message = "Название товара не должно превышать 255 символов")
    @Column(nullable = false, length = 255)
    private String name;

    @NotBlank(message = "Категория товара обязательна")
    @Size(max = 100, message = "Название категории не должно превышать 100 символов")
    @Column(nullable = false, length = 100)
    private String category;

    @NotNull(message = "Цена товара обязательна")
    @Positive(message = "Цена должна быть положительной")
    @Column(nullable = false)
    private BigDecimal price;

    @Min(value = 0, message = "Количество товара не может быть отрицательным")
    @Column(name = "available_stock")
    private Integer availableStock = 0;

    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime lastUpdateDate = LocalDateTime.now();

    @NotNull(message = "Поставщик товара обязателен")
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnoreProperties("products")
    private Supplier supplier;
}