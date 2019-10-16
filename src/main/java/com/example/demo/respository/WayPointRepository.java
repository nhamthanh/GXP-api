package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.WayPointEntity;

public interface WayPointRepository extends JpaRepository<WayPointEntity, Long> {

}
