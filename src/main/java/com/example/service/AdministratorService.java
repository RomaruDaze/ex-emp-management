package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository adminRepository;

    public Administrator findByMailAddressAndPassword(String mail_address, String password) {
        return adminRepository.findByMailAddressAndPassword(mail_address, password);
    }

    public void save(Administrator admin) {
        adminRepository.save(admin);
    }

    public List<Administrator> findAll() {
        return adminRepository.findAll();
    }
}
