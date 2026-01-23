package com.example.electronicStoreProject.service;

import com.example.electronicStoreProject.entity.Address;
import com.example.electronicStoreProject.exception.ResourceNotFoundException;
import com.example.electronicStoreProject.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        log.info("Запрос на получение всех адресов");
        List<Address> addresses = addressRepository.findAll();
        log.info("Успешно получено {} адресов", addresses.size());
        return addresses;
    }

    public Address getAddressById(Long id) {
        log.info("Получение адреса по ID: {}", id);
        return addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Адрес с ID {} не найден", id);
                    return new ResourceNotFoundException("Адрес c id " + id + " не найден");
                });
    }

    public Address addAddress(Address address) {
        log.info("Попытка добавить новый адрес: {}", address);
        Address savedAddress = addressRepository.save(address);
        log.info("Успешно добавлен новый адрес с ID: {}", savedAddress.getId());
        return savedAddress;
    }

    public Address updateAddress(Long id, Address updatedAddress) {
        log.info("Попытка обновления адреса с ID: {}", id);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Адрес с ID {} не найден", id);
                    return new ResourceNotFoundException("Адрес c id " + id + " не найден");
                });
        log.info("Обновление полей адреса с ID: {}", id);
        address.setStreet(updatedAddress.getStreet());
        address.setCity(updatedAddress.getCity());
        address.setCountry(updatedAddress.getCountry());
        Address savedAddress = addressRepository.save(address);
        log.info("Успешно обновлен адрес с ID: {}", id);
        return savedAddress;
    }

    public void deleteAddress(Long id) {
        log.info("Попытка удаления адреса с ID: {}", id);
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Адрес с ID {} не найден для удаления", id);
                    return new ResourceNotFoundException("Адрес с id " + id + " не найден");
                });
        addressRepository.delete(address);
        log.info("Успешно удален адрес с ID: {}", id);
    }
}

