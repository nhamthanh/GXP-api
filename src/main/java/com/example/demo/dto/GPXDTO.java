package com.example.demo.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class GPXDTO {

    private Long id;

    private String name;

    private String desc;

    private ZonedDateTime time;

    private PersonDTO author;

    private List<LinkDTO> links;

    private List<TrackDTO> tracks;

    private List<WayPointDTO> waypoints;

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

    public PersonDTO getAuthor() {
        return author;
    }

    public void setAuthor(PersonDTO author) {
        this.author = author;
    }

    public List<LinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<LinkDTO> links) {
        this.links = links;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<WayPointDTO> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<WayPointDTO> waypoints) {
        this.waypoints = waypoints;
    }

}
