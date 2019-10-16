package com.example.demo.dto;

public class LinkDTO {

    private Long id;

    private String href;

    private String text;

    private GPXDTO gps;

    public LinkDTO() {
        super();
    }

    public LinkDTO(String href, String text) {
        super();
        this.href = href;
        this.text = text;
    }

    public LinkDTO(String href, String text, Long id) {
        super();
        this.href = href;
        this.text = text;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GPXDTO getGps() {
        return gps;
    }

    public void setGps(GPXDTO gps) {
        this.gps = gps;
    }
}
