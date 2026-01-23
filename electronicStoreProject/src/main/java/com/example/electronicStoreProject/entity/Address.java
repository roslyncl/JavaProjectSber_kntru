package com.example.electronicStoreProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Улица не может быть пустой")
    @Size(max = 255, message = "Название улицы не должно превышать 255 символов")
    @Column(nullable = false, length = 255)
    private String street;

    @NotBlank(message = "Город не может быть пустым")
    @Size(max = 100, message = "Название города не должно превышать 100 символов")
    @Column(nullable = false, length = 100)
    private String city;

    @NotBlank(message = "Страна не может быть пустой")
    @Size(max = 100, message = "Название страны не должно превышать 100 символов")
    @Column(nullable = false, length = 100)
    private String country;
}