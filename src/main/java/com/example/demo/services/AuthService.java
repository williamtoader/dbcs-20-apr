package com.example.demo.services;

import com.example.demo.model.ApiKey;
import com.example.demo.repositories.KeyRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final KeyRepository repository;

    public Boolean validateKey(String key) {
        return repository.findById(key).isPresent();
    }

    public ApiKey addKey() {
        String generatedKey = UUID.randomUUID().toString();
        return repository.save(new ApiKey(generatedKey));
    }

    public void deleteKey(String key) {
        repository.deleteById(key);
    }

    public List<ApiKey> getAllApiKeys() {
        return repository.findAll();
    }

    @PostConstruct
    private void afterMounting() {
        System.out.println(addKey().getApiKey());
    }
}
