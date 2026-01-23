package com.example.electronicStoreProject.service;

import com.example.electronicStoreProject.entity.Client;
import com.example.electronicStoreProject.exception.ResourceNotFoundException;
import com.example.electronicStoreProject.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        log.info("Запрос на получение всех клиентов");
        List<Client> clients = clientRepository.findAll();
        log.info("Успешно получено {} клиентов", clients.size());
        return clients;
    }

    public Client getClientById(Long id) {
        log.info("Получение клиента по ID: {}", id);
        return clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Клиент с ID {} не найден", id);
                    return new ResourceNotFoundException("Клиент c id " + id + " не найден");
                });
    }

    public Client addClient(Client client) {
        log.info("Попытка добавить нового клиента: {}", client);
        Client savedClient = clientRepository.save(client);
        log.info("Успешно добавлен новый клиент с ID: {}", savedClient.getId());
        return savedClient;
    }

    public Client updateClient(Long id, Client updatedClient) {
        log.info("Попытка обновления клиента с ID: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Клиент с ID {} не найден", id);
                    return new ResourceNotFoundException("Клиент c id " + id + " не найден");
                });
        log.info("Обновление полей клиента с ID: {}", id);
        client.setName(updatedClient.getName());
        client.setSurname(updatedClient.getSurname());
        client.setBirthday(updatedClient.getBirthday());
        client.setGender(updatedClient.getGender());
        client.setRegistrationDate(updatedClient.getRegistrationDate());
        client.setAddress(updatedClient.getAddress());
        Client savedClient = clientRepository.save(client);
        log.info("Успешно обновлен клиент с ID: {}", id);
        return savedClient;
    }

    public void deleteClient(Long id) {
        log.info("Попытка удаления клиента с ID: {}", id);
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Клиент с ID {} не найден для удаления", id);
                    return new ResourceNotFoundException("Клиент с id " + id + " не найден");
                });
        clientRepository.delete(client);
        log.info("Успешно удален клиент с ID: {}", id);
    }

    public List<Client> getClientsByCity(String city) {
        log.info("Запрос клиентов по городу: {}", city);
        List<Client> clients = clientRepository.findClientsByCity(city);
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("Клиенты, проживающие в городе " + city + ", не найдены");
        }
        log.info("Найдено клиентов: {}", clients.size());
        return clients;
    }
}




