package com.example.electronicStoreProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя клиента обязательно")
    @Size(max = 100, message = "Имя клиента не должно превышать 100 символов")
    @Column(name = "client_name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Фамилия клиента обязательна")
    @Size(max = 100, message = "Фамилия клиента не должна превышать 100 символов")
    @Column(name = "client_surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender", length = 1)
    private String gender;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @NotNull(message = "Адрес обязателен")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false, unique = true)
    private Address address;
}