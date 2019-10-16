package com.example.demo.converter;

import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.demo.dto.TrackDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.TrackEntity;

import io.jenetics.jpx.Track;

public class TrackConverter {
    /**
     * Convert track data from gpx to entities
     * 
     * @param tracks
     * @param trackEntities
     * @param gpxEntity
     */
    public static void gpxToEntities(List<Track> tracks, Set<TrackEntity> trackEntities, GPXEntity gpxEntity) {
        for (Track track : tracks) {
            TrackEntity trackEntity = new TrackEntity();
            trackEntity.setComment(track.getComment().orElse(null));
            trackEntity.setName(track.getName().orElse(null));
            trackEntity.setSource(track.getSource().orElse(null));
            trackEntity.setDescription(track.getDescription().orElse(null));
            trackEntity.setType(track.getType().orElse(null));
            // Create track segment for track
            TrackSegmentConverter.gpxToEntities(track.getSegments(), trackEntity);
            trackEntity.setGpx(gpxEntity);
            trackEntities.add(trackEntity);
        }
        gpxEntity.setTracks(trackEntities);
    }

    /**
     * Convert track data from entities to DTO
     * 
     * @param entities
     * @param dtos
     */
    public static void entitiesToDTOs(Set<TrackEntity> entities, List<TrackDTO> dtos) {
        if (CollectionUtils.isEmpty(entities)) {
            return;
        } else {
            for (TrackEntity entity : entities) {
                TrackDTO dto = new TrackDTO();
                dto.setName(entity.getName());
                dto.setSource(entity.getSource());
                dto.setDescription(entity.getDescription());
                dto.setComment(entity.getComment());
                dto.setType(entity.getType());
                TrackSegmentConverter.entitiesToDTOs(entity.getTrackSegmentEntities(), dto.getTrackSegments());
                dtos.add(dto);
            }
        }
    }
}
