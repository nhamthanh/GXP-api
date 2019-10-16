package com.example.demo.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.demo.converter.TrackConverter;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.TrackEntity;
import com.example.demo.service.TrackService;

import io.jenetics.jpx.Track;

@Service
public class TrackServiceImpl implements TrackService {

    @Override
    public void createTrack(List<Track> tracks, GPXEntity gpxEntity) {
        Set<TrackEntity> trackEntities = new HashSet<TrackEntity>();
        TrackConverter.gpxToEntities(tracks, trackEntities, gpxEntity);
    }

}
