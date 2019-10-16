package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TrackDTO {
    private Long id;

    private String description;

    private String name;

    private String comment;

    private String source;

    private String type;

    private GPXDTO gps;

    private List<TrackSegmentDTO> trackSegments;

    public TrackDTO() {
        super();
        trackSegments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GPXDTO getGps() {
        return gps;
    }

    public void setGps(GPXDTO gps) {
        this.gps = gps;
    }

    public List<TrackSegmentDTO> getTrackSegments() {
        return trackSegments;
    }

    public void setTrackSegments(List<TrackSegmentDTO> trackSegments) {
        this.trackSegments = trackSegments;
    }

}
