package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TrackSegmentEntity;

public interface TrackSegmentRepository extends JpaRepository<TrackSegmentEntity, Long> {

}
