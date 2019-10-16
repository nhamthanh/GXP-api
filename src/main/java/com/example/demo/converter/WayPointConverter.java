package com.example.demo.converter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.WayPointDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.WayPointEntity;

import io.jenetics.jpx.WayPoint;

public class WayPointConverter {
    /**
     * convert way point data from gpx file to entity
     */
    public static void gpxToEntities(List<WayPoint> wayPoints, Collection<WayPointEntity> waypoints,
            GPXEntity gpxEntity) {
        for (WayPoint wayPoint : wayPoints) {
            WayPointEntity wayPointEntity = new WayPointEntity(String.valueOf(wayPoint.getLatitude()),
                    String.valueOf(wayPoint.getLongitude()), wayPoint.getName().orElse(null),
                    wayPoint.getSymbol().orElse(null));
            wayPointEntity.setGpx(gpxEntity);
            waypoints.add(wayPointEntity);
        }
    }

    /**
     * Convert way point data from entities to dto
     * 
     * @param wayPointDTOs
     * @param wayPointEntities
     */
    public static void entitiesToDTOs(Set<WayPointEntity> wayPointEntities, List<WayPointDTO> wayPointDTOs) {
        for (WayPointEntity wayPointEntity : wayPointEntities) {
            WayPointDTO dto = new WayPointDTO(wayPointEntity.getLat(), wayPointEntity.getLon(),
                    wayPointEntity.getName(), wayPointEntity.getSym());
            dto.setId(wayPointEntity.getId());
            wayPointDTOs.add(dto);
        }
    }
}
