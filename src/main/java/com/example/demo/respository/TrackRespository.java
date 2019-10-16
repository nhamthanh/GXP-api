package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrackEntity;

public interface TrackRespository extends JpaRepository<TrackEntity, Long> {

}
