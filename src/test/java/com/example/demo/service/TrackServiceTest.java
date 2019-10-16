package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.GPXEntity;
import com.example.demo.utils.CompareUtil;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackServiceTest {
    @Autowired
    TrackService trackService;

    GPXEntity gpxEntity;

    GPX gpx;

    @Before
    public void setup() throws IOException {
        gpx = GPX.read("sample/test2.gpx");
        gpxEntity = new GPXEntity();
    }

    @Test
    public void createTracksTest() {
        List<Track> tracks = gpx.getTracks();
        trackService.createTrack(tracks, gpxEntity);
        CompareUtil.gPXTracksWithEntity(tracks, gpxEntity.getTracks());
    }
}
