package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.LinkEntity;
import com.example.demo.entity.TrackEntity;
import com.example.demo.entity.WayPointEntity;

public class DataBuilder {
    public static GPXEntity buildGPXEntity() {
        GPXEntity entity = new GPXEntity();
        // metadata
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault());
        entity.setName("gpx entity");
        entity.setDesc("entity created for test");
        entity.setTime(now);
        entity.setWaypoints(buildWayPointEntity());
        entity.setTracks(buildTrackEntity());
        return entity;
    }

    public static Set<WayPointEntity> buildWayPointEntity() {
        Set<WayPointEntity> wayPointEntities = new LinkedHashSet<>();
        WayPointEntity wayPointEntity1 = new WayPointEntity();
        wayPointEntity1.setLat("42.220683708460996");
        wayPointEntity1.setLon("-1.4582070708274841");
        wayPointEntity1.setName("Entorno de Piskerra");
        wayPointEntity1.setSym("/static/wpt/Waypoint");
        WayPointEntity wayPointEntity2 = new WayPointEntity();
        wayPointEntity1.setLat("42.220683708460996");
        wayPointEntity1.setLon("-1.4582070708274841");
        wayPointEntity1.setName("Entorno de Piskerra");
        wayPointEntity1.setSym("/static/wpt/Waypoint");
        wayPointEntities.add(wayPointEntity1);
        wayPointEntities.add(wayPointEntity2);
        return wayPointEntities;
    }

    public static Set<TrackEntity> buildTrackEntity() {
        Set<TrackEntity> trackEntities = new LinkedHashSet<>();
        TrackEntity trackEntity1 = new TrackEntity();
        trackEntity1.setType("type 1");
        trackEntity1.setComment("comment 1");
        trackEntity1.setName("track 1");
        trackEntity1.setDescription("Track for first vehicle");
        TrackEntity trackEntity2 = new TrackEntity();
        trackEntity2.setType("type 2");
        trackEntity2.setComment("comment 2");
        trackEntity2.setName("track 2");
        trackEntity2.setName("Track for second vehicle");
        trackEntities.add(trackEntity1);
        trackEntities.add(trackEntity2);
        return trackEntities;
    }

    public static Set<LinkEntity> buildLinkEntity() {
        Set<LinkEntity> linkEntities = new LinkedHashSet<>();
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setHref("google.com");
        linkEntity.setText("text 1");
        linkEntities.add(linkEntity);
        return linkEntities;
    }
}
