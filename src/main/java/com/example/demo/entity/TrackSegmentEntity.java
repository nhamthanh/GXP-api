package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TrackSegmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private TrackEntity track;

    @OneToMany(mappedBy = "trackSegment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrackPointEntity> trackPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrackEntity getTrack() {
        return track;
    }

    public void setTrack(TrackEntity track) {
        this.track = track;
    }

    public Set<TrackPointEntity> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(Set<TrackPointEntity> trackPoints) {
        this.trackPoints = trackPoints;
    }

}
