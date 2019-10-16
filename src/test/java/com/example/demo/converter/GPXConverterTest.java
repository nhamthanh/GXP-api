package com.example.demo.converter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.GPXDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.service.WayPointService;
import com.example.demo.utils.CompareUtil;
import com.example.demo.utils.DataBuilder;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Metadata;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPXConverterTest {

    GPX gpx;
    GPXEntity gpxEntity;

    @Autowired
    WayPointService wayPointService;

    @Before
    public void setup() throws IOException {
        gpx = GPX.read("sample/test2.gpx");
        gpxEntity = new GPXEntity();
    }

    @Test
    public void gpxToEntityTest() {
        Metadata meta = gpx.getMetadata().get();
        GPXConverter.gpxToEntity(gpx, gpxEntity);
        assertEquals(meta.getName().get(), gpxEntity.getName());
        assertEquals(meta.getDescription().get(), gpxEntity.getDesc());
        assertEquals(meta.getTime().get(), gpxEntity.getTime());
    }

    @Test
    public void entityToDTOMetaLoadTest() {
        GPXEntity entity = new GPXEntity();
        GPXConverter.gpxToEntity(gpx, entity);
        GPXDTO gPXDTO = new GPXDTO();
        GPXConverter.entityToDTOMetaLoad(entity, gPXDTO);
        CompareUtil.metadataEntityWithDTO(entity, gPXDTO);
    }

    @Test
    public void entityToDTOFullLoadTest() {
        GPXEntity entity = DataBuilder.buildGPXEntity();
        GPXDTO gPXDTO = new GPXDTO();
        GPXConverter.entityToDTOFullLoad(entity, gPXDTO);
        CompareUtil.metadataEntityWithDTO(entity, gPXDTO);
        CompareUtil.wayPointEntitiesWithDTO(entity.getWaypoints(), gPXDTO.getWaypoints());
        CompareUtil.trackEntitiesWithDTO(entity.getTracks(), gPXDTO.getTracks());
    }

}
