package com.group11.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group11.models.SecretaryRequest;

public interface SecretaryRequestRepository extends JpaRepository<SecretaryRequest,Integer>{
    
}
