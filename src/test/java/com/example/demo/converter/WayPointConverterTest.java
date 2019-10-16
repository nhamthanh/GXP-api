package com.example.demo.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.WayPointDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.WayPointEntity;
import com.example.demo.utils.CompareUtil;
import com.example.demo.utils.DataBuilder;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;

@RunWith(SpringRunner.class)
public class WayPointConverterTest {
    GPX gpx;
    GPXEntity gpxEntity;

    Set<WayPointEntity> waypointEntities;

    @Before
    public void setup() throws IOException {
        gpx = GPX.read("sample/test2.gpx");
        gpxEntity = new GPXEntity();
        waypointEntities = new LinkedHashSet<>();
    }

    @Test
    public void gpxToEntityTest() {
        List<WayPoint> points = gpx.getWayPoints();
        WayPointConverter.gpxToEntities(points, waypointEntities, gpxEntity);
        CompareUtil.gPXWayPointsWithEntity(points, waypointEntities);
    }

    @Test
    public void entitiesToDTOsTest() {
        // prepare data
        List<WayPointDTO> wayPointDTOs = new ArrayList<>();
        waypointEntities = DataBuilder.buildWayPointEntity();
        // execute test method
        WayPointConverter.entitiesToDTOs(waypointEntities, wayPointDTOs);
        CompareUtil.wayPointEntitiesWithDTO(waypointEntities, wayPointDTOs);
    }
}
