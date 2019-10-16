package com.example.demo.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.TrackDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.TrackEntity;
import com.example.demo.utils.CompareUtil;
import com.example.demo.utils.DataBuilder;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;

@RunWith(SpringRunner.class)
public class TrackConverterTest {
    GPX gpx;
    GPXEntity gpxEntity;

    Set<TrackEntity> trackEntities;

    @Before
    public void setup() throws IOException {
        gpx = GPX.read("sample/test2.gpx");
        gpxEntity = new GPXEntity();
        trackEntities = new LinkedHashSet<>();
    }

    @Test
    public void gpxToEntityTest() {
        List<Track> points = gpx.getTracks();
        TrackConverter.gpxToEntities(points, trackEntities, gpxEntity);
        CompareUtil.gPXTracksWithEntity(points, trackEntities);
    }

    @Test
    public void entitiesToDTOsTest() {
        // prepare data
        List<TrackDTO> trackDTOs = new ArrayList<>();
        trackEntities = DataBuilder.buildTrackEntity();
        // execute test method
        TrackConverter.entitiesToDTOs(trackEntities, trackDTOs);
        CompareUtil.trackEntitiesWithDTO(trackEntities, trackDTOs);
    }
}
