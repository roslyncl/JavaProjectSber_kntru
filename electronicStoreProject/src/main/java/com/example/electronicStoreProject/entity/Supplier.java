package com.example.electronicStoreProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "supplier")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название поставщика обязательно")
    @Size(max = 255, message = "Название поставщика не должно превышать 255 символов")
    @Column(nullable = false, length = 255)
    private String name;

    @NotNull(message = "Адрес поставщика обязателен")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true)
    private Address address;

    @NotBlank(message = "Номер телефона обязателен")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;
}