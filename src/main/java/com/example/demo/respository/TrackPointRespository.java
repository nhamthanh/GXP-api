package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrackPointEntity;

public interface TrackPointRespository extends JpaRepository<TrackPointEntity, Long> {

}
