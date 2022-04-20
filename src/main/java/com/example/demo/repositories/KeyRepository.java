package com.example.demo.repositories;

import com.example.demo.model.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<ApiKey, String> {
}
