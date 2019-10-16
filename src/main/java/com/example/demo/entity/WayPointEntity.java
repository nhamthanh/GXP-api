package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class WayPointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String lat;

    private String lon;

    private String name;

    private String sym;

    @ManyToOne
    @JoinColumn(name = "gpx_id")
    private GPXEntity gpx;

    public WayPointEntity() {
        super();
    }

    public WayPointEntity(String lat, String lon, String name, String sym) {
        super();
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.sym = sym;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public GPXEntity getGpx() {
        return gpx;
    }

    public void setGpx(GPXEntity gpx) {
        this.gpx = gpx;
    }

}
