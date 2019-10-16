package com.example.demo.dto;

public class WayPointDTO {

    private Long id;

    private String lat;

    private String lon;

    private String name;

    private String sym;

    public WayPointDTO(String lat, String lon, String name, String sym) {
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

}
