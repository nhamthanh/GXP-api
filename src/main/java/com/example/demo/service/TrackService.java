package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.GPXEntity;

import io.jenetics.jpx.Track;

public interface TrackService {
    public void createTrack(List<Track> tracks, GPXEntity gpxEntity);
}
