package com.example.demo.converter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.demo.dto.TrackPointDTO;
import com.example.demo.dto.TrackSegmentDTO;
import com.example.demo.entity.TrackPointEntity;
import com.example.demo.entity.TrackSegmentEntity;
import com.example.demo.utils.GPXUtils;

import io.jenetics.jpx.WayPoint;

public class TrackPointConverter {
    /**
     * convert track point data from gpx to entities
     * 
     * @param trackSegments
     * @param trackEntity
     */
    public static void gpxToEntities(List<WayPoint> trackPoints, TrackSegmentEntity trackSegmentEntity) {
        if (CollectionUtils.isEmpty(trackPoints)) {
            return;
        }
        // Create track points for Track Segment
        Set<TrackPointEntity> trackPointEntities = new LinkedHashSet<>();
        for (WayPoint trackPoint : trackPoints) {
            TrackPointEntity trackPointEntity = new TrackPointEntity(String.valueOf(trackPoint.getLatitude()),
                    String.valueOf(trackPoint.getLongitude()),
                    GPXUtils.lengthToString(trackPoint.getElevation().orElse(null)), trackPoint.getTime().orElse(null));
            trackPointEntity.setTrackSegment(trackSegmentEntity);
            trackPointEntities.add(trackPointEntity);
        }
        trackSegmentEntity.setTrackPoints(trackPointEntities);
    }

    /**
     * convert track point data from entity to dtos
     * 
     * @param trackSegments
     * @param trackEntity
     */
    public static void entitiesToDTOs(Set<TrackPointEntity> entities, TrackSegmentDTO dto) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        List<TrackPointDTO> trackPointDTOs = new ArrayList<>();
        // Create track points for Track Segment
        for (TrackPointEntity entity : entities) {
            TrackPointDTO trackPointDTO = new TrackPointDTO(entity.getLat(), entity.getLon(), entity.getEle(),
                    entity.getTime());
            trackPointDTO.setId(entity.getId());
            trackPointDTOs.add(trackPointDTO);
        }
        dto.setTrackPoints(trackPointDTOs);
    }
}
