package com.example.demo.converter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.example.demo.dto.LinkDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.LinkEntity;

import io.jenetics.jpx.Link;

public class LinkConverter {

    /**
     * Convert gpx link data to link entity
     * 
     * @param gpx
     * @param links
     * @param gpxEntity
     */
    public static void gpxToEntity(List<Link> links, GPXEntity gpxEntity) {
        // check null
        if (CollectionUtils.isEmpty(links)) {
            return;
        }
        Set<LinkEntity> linkEntitys = new LinkedHashSet<>();
        for (Link link : links) {
            LinkEntity linkEntity = new LinkEntity(String.valueOf(link.getHref()), link.getText().orElse(null));
            linkEntitys.add(linkEntity);
            linkEntity.setGpx(gpxEntity);
        }
        gpxEntity.setLinks(linkEntitys);
    }

    /**
     * Convert link entity data to link dto
     * 
     * @param linkDTOs
     * @param linkEntities
     */
    public static void entityToDTO(Set<LinkEntity> linkEntities, List<LinkDTO> linkDTOs) {
        // check null
        if (CollectionUtils.isEmpty(linkEntities)) {
            return;
        }
        for (LinkEntity linkEntity : linkEntities) {
            LinkDTO link = new LinkDTO(linkEntity.getHref(), linkEntity.getText(), linkEntity.getId());
            linkDTOs.add(link);
        }
    }

}
