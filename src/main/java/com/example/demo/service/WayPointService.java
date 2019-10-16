package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.GPXEntity;

import io.jenetics.jpx.WayPoint;

public interface WayPointService {
    public void createWayPoints(List<WayPoint> wayPoints, GPXEntity gpxEntity);
}
