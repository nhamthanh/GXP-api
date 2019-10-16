package com.example.demo.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.GPXDTO;
import com.example.demo.dto.LinkDTO;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.TrackDTO;
import com.example.demo.dto.WayPointDTO;
import com.example.demo.entity.GPXEntity;

import io.jenetics.jpx.GPX;

public class GPXConverter {
    /**
     * Convert metadata from gpx to entity
     * 
     * @param gpx
     * @param entity
     */
    public static void gpxToEntity(GPX gpx, GPXEntity entity) {
        entity.setName(gpx.getMetadata().get().getName().orElse(null));
        entity.setTime(gpx.getMetadata().get().getTime().orElse(null));
        entity.setDesc(gpx.getMetadata().get().getDescription().orElse(null));
        PersonConverter.gpxToEntity(gpx.getMetadata().get().getAuthor().orElse(null), entity);
    }

    /**
     * Convert metadata from gpx entity to dto
     * 
     * @param gpxEntity
     * @param gPXDTO
     */
    public static void entityToDTOMetaLoad(GPXEntity gpxEntity, GPXDTO gPXDTO) {
        metaLoad(gpxEntity, gPXDTO);
    }

    /**
     * Convert metadata, track, waypoint from gpx entity to dto
     * 
     * @param gpxEntity
     * @param gPXDTO
     */
    public static void entityToDTOFullLoad(GPXEntity gpxEntity, GPXDTO gPXDTO) {
        metaLoad(gpxEntity, gPXDTO);
        childLoad(gpxEntity, gPXDTO);
    }

    /**
     * Convert metadata from gpx entity to dto (detail function)
     * 
     * @param gpxEntity
     * @param gPXDTO
     */
    private static void metaLoad(GPXEntity gpxEntity, GPXDTO gPXDTO) {
        if (gpxEntity == null) {
            gPXDTO = null;
        } else {
            gPXDTO.setId(gpxEntity.getId());
            gPXDTO.setName(gpxEntity.getName());
            gPXDTO.setDesc(gpxEntity.getDesc());
            gPXDTO.setTime(gpxEntity.getTime());
            // convert author data
            gPXDTO.setAuthor(new PersonDTO());
            PersonConverter.entityToDTO(gpxEntity.getAuthor(), gPXDTO.getAuthor());
        }
    }

    /**
     * Convert metadata, track, waypoint from gpx entity to dto (detail function)
     * 
     * @param gpxEntity
     * @param gPXDTO
     */
    private static void childLoad(GPXEntity gpxEntity, GPXDTO gPXDTO) {
        // Set link data
        List<LinkDTO> linkDTOs = new ArrayList<LinkDTO>();
        gPXDTO.setLinks(linkDTOs);
        LinkConverter.entityToDTO(gpxEntity.getLinks(), linkDTOs);
        // Set track data
        List<TrackDTO> trackDTOs = new ArrayList<TrackDTO>();
        TrackConverter.entitiesToDTOs(gpxEntity.getTracks(), trackDTOs);
        gPXDTO.setTracks(trackDTOs);
        // Set way point data
        List<WayPointDTO> wayPointDTOs = new ArrayList<WayPointDTO>();
        WayPointConverter.entitiesToDTOs(gpxEntity.getWaypoints(), wayPointDTOs);
        gPXDTO.setWaypoints(wayPointDTOs);
    }

    /**
     * Convert metadata, track, waypoint from list of gpx entities to dtos
     * 
     * @param gpxEntities
     * @param gPXDTOs
     */
    public static void gpxsToDTOs(List<GPXEntity> gpxEntities, List<GPXDTO> gPXDTOs) {
        for (GPXEntity gpxEntity : gpxEntities) {
            GPXDTO gPXDTO = new GPXDTO();
            entityToDTOMetaLoad(gpxEntity, gPXDTO);
            gPXDTOs.add(gPXDTO);
        }
    }
}
