package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.GPXDTO;

import io.jenetics.jpx.GPX;

public interface GPXService {
    public GPXDTO saveGpx(GPX gpx);

    public List<GPXDTO> getLastedTrack(int page);

    public GPXDTO getDetail(long id);
}
