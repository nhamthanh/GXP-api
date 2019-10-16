package com.example.demo.serviceImpl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.converter.WayPointConverter;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.WayPointEntity;
import com.example.demo.service.WayPointService;

import io.jenetics.jpx.WayPoint;

@Service
public class WayPointServiceImpl implements WayPointService {

    /**
     * Create WayPoints model return List<WayPointEntity>
     */
    @Override
    public void createWayPoints(List<WayPoint> wayPoints, GPXEntity gpxEntity) {
        Set<WayPointEntity> wayPointEntities = new LinkedHashSet<>();
        WayPointConverter.gpxToEntities(wayPoints, wayPointEntities, gpxEntity);
        gpxEntity.setWaypoints(wayPointEntities);
    }

}
