package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String href;

    private String text;

    @ManyToOne
    @JoinColumn(name = "gpx_id")
    private GPXEntity gpx;

    public LinkEntity() {
        super();
    }

    public LinkEntity(String href, String text) {
        super();
        this.href = href;
        this.text = text;
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

    public GPXEntity getGpx() {
        return gpx;
    }

    public void setGpx(GPXEntity gpx) {
        this.gpx = gpx;
    }

}
