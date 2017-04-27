package com.example.service;

import com.example.repository.DemoRepository;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    private final DemoRepository repository;

    DemoService(DemoRepository repository) {
        this.repository = repository;
    }

    public int findNumber() {
        return repository.findNumber();
    }

}
