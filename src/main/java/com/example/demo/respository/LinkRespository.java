package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LinkEntity;

public interface LinkRespository extends JpaRepository<LinkEntity, Long> {

}
