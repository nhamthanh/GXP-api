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
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private String name;

    private String comment;

    private String source;

    private String type;

    @ManyToOne
    @JoinColumn(name = "gpx_id")
    private GPXEntity gpx;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrackSegmentEntity> trackSegmentEntities;

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

    public GPXEntity getGpx() {
        return gpx;
    }

    public void setGpx(GPXEntity gpx) {
        this.gpx = gpx;
    }

    public Set<TrackSegmentEntity> getTrackSegmentEntities() {
        return trackSegmentEntities;
    }

    public void setTrackSegmentEntities(Set<TrackSegmentEntity> trackSegmentEntities) {
        this.trackSegmentEntities = trackSegmentEntities;
    }

}
