package com.example.demo.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.LinkDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.LinkEntity;
import com.example.demo.utils.CompareUtil;
import com.example.demo.utils.DataBuilder;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Link;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkConverterTest {
    GPX gpx;
    GPXEntity gpxEntity;

    Set<LinkEntity> linkEntities;

    @Before
    public void setup() throws IOException {
        gpx = GPX.read("sample/test2.gpx");
        gpxEntity = new GPXEntity();
        linkEntities = new LinkedHashSet<>();
    }

    @Test
    public void gpxToEntityTest() {
        List<Link> links = gpx.getMetadata().get().getLinks();
        LinkConverter.gpxToEntity(links, gpxEntity);
        CompareUtil.gPXLinksWithEntities(links, gpxEntity.getLinks());
    }

    @Test
    public void entitiesToDTOsTest() {
        // prepare data
        List<LinkDTO> linkDTOs = new ArrayList<>();
        linkEntities = DataBuilder.buildLinkEntity();
        // execute test method
        LinkConverter.entityToDTO(linkEntities, linkDTOs);
        CompareUtil.linkEntitiesWithDTO(linkEntities, linkDTOs);
    }
}
