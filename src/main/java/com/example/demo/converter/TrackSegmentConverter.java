package com.example.demo.converter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.demo.dto.TrackSegmentDTO;
import com.example.demo.entity.TrackEntity;
import com.example.demo.entity.TrackSegmentEntity;

import io.jenetics.jpx.TrackSegment;

public class TrackSegmentConverter {
    /**
     * convert track segment data from gpx to entities
     * 
     * @param trackSegments
     * @param trackEntity
     */
    public static void gpxToEntities(List<TrackSegment> trackSegments, TrackEntity trackEntity) {
        if (CollectionUtils.isEmpty(trackSegments)) {
            return;
        }
        // Create track segments
        Set<TrackSegmentEntity> trackSegmentEntities = new LinkedHashSet<>();
        for (TrackSegment trackSegment : trackSegments) {
            TrackSegmentEntity trackSegmentEntity = new TrackSegmentEntity();
            trackSegmentEntities.add(trackSegmentEntity);
            // Create track points for Track Segment
            TrackPointConverter.gpxToEntities(trackSegment.getPoints(), trackSegmentEntity);
            trackSegmentEntity.setTrack(trackEntity);
        }
        trackEntity.setTrackSegmentEntities(trackSegmentEntities);
    }

    /**
     * convert track segment data from entity to dtos
     * 
     * @param trackSegments
     * @param trackEntity
     */
    public static void entitiesToDTOs(Set<TrackSegmentEntity> entities, List<TrackSegmentDTO> dtos) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        // Create track segments
        for (TrackSegmentEntity entity : entities) {
            TrackSegmentDTO trackSegmentDTO = new TrackSegmentDTO();
            trackSegmentDTO.setId(entity.getId());
            dtos.add(trackSegmentDTO);
            // Create track points for Track Segment
            TrackPointConverter.entitiesToDTOs(entity.getTrackPoints(), trackSegmentDTO);
        }
    }
}
