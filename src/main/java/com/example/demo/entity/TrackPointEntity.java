package com.example.demo.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TrackPointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lat;

    private String lon;

    private String ele;

    private ZonedDateTime time;

    @ManyToOne
    @JoinColumn(name = "trackSegment_id")
    private TrackSegmentEntity trackSegment;

    public TrackPointEntity() {
        super();
    }

    public TrackPointEntity(String lat, String lon, String ele, ZonedDateTime time) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.ele = ele;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getEle() {
        return ele;
    }

    public void setEle(String ele) {
        this.ele = ele;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public TrackSegmentEntity getTrackSegment() {
        return trackSegment;
    }

    public void setTrackSegment(TrackSegmentEntity trackSegment) {
        this.trackSegment = trackSegment;
    }

}
