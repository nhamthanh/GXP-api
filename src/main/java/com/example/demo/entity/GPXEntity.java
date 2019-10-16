package com.example.demo.entity;

import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class GPXEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String desc;

    @OneToOne(mappedBy = "gpx", cascade = CascadeType.ALL)
    private PersonEntity author;

    private ZonedDateTime time;

    @OneToMany(mappedBy = "gpx", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LinkEntity> links;

    @OneToMany(mappedBy = "gpx", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TrackEntity> tracks;

    @OneToMany(mappedBy = "gpx", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<WayPointEntity> waypoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public Set<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(Set<LinkEntity> links) {
        this.links = links;
    }

    public Set<TrackEntity> getTracks() {
        return tracks;
    }

    public void setTracks(Set<TrackEntity> tracks) {
        this.tracks = tracks;
    }

    public Set<WayPointEntity> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(Set<WayPointEntity> waypoints) {
        this.waypoints = waypoints;
    }

    public PersonEntity getAuthor() {
        return author;
    }

    public void setAuthor(PersonEntity author) {
        this.author = author;
    }

}
