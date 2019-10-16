package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class TrackSegmentDTO {

    private Long id;

    private TrackDTO track;

    private List<TrackPointDTO> trackPoints;

    public TrackSegmentDTO() {
        super();
        trackPoints = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackDTO getTrack() {
        return track;
    }

    public void setTrack(TrackDTO track) {
        this.track = track;
    }

    public List<TrackPointDTO> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPointDTO> trackPoints) {
        this.trackPoints = trackPoints;
    }

}
