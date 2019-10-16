package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.example.demo.dto.GPXDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.respository.GpxRespository;
import com.example.demo.utils.CompareUtil;

import io.jenetics.jpx.GPX;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GPXServiceTest {

    @Autowired
    GPXService gPXService;

    @Autowired
    GpxRespository gpxRespository;

    @Mock
    Environment env;

    GPX gpx1, gpx2, gpx3;

    static {
        System.setProperty("spring.num-per-page", "2");
    }

    @Before
    public void initData() {
        try {
            gpx1 = GPX.read("sample/test1.gpx");
            gpx2 = GPX.read("sample/test2.gpx");
            gpx3 = GPX.read("sample/test3.gpx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void clearData() {
        gpxRespository.deleteAll();
    }

    @Test
    public void saveGpxTest_noAuthor() {
        GPXDTO actual = gPXService.saveGpx(gpx1);
        CompareUtil.gPXMetadataWithDTO(gpx1, actual);
        CompareUtil.gPXLinksWithDTO(gpx1.getMetadata().get().getLinks(), actual.getLinks());
        CompareUtil.gPXWayPointsWithDTO(gpx1.getWayPoints(), actual.getWaypoints());
        CompareUtil.gPXTracksWithDTO(gpx1.getTracks(), actual.getTracks());
    }

    @Test
    public void saveGpxTest_author() {
        GPXDTO actual = gPXService.saveGpx(gpx2);
        CompareUtil.gPXMetadataWithDTO(gpx2, actual);
        CompareUtil.gPXLinksWithDTO(gpx2.getMetadata().get().getLinks(), actual.getLinks());
        CompareUtil.gPXWayPointsWithDTO(gpx2.getWayPoints(), actual.getWaypoints());
        CompareUtil.gPXTracksWithDTO(gpx2.getTracks(), actual.getTracks());
    }

    @Test
    public void getLastedTrack_noRecord() {
        List<GPXDTO> actual = gPXService.getLastedTrack(1);
        assertEquals(0, actual.size());
    }

    @Test
    @Transactional
    public void getLastedTrack_page1() {
        gPXService.saveGpx(gpx1);
        gPXService.saveGpx(gpx3);
        gPXService.saveGpx(gpx2);
        List<GPX> expect = new ArrayList<>();
        expect.add(gpx1);
        expect.add(gpx2);
        List<GPXDTO> actual = gPXService.getLastedTrack(0);
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            CompareUtil.gPXMetadataWithDTO(expect.get(i), actual.get(i));
        });
    }

    @Test
    @Transactional
    public void getLastedTrack_page2() {
        gPXService.saveGpx(gpx1);
        gPXService.saveGpx(gpx3);
        gPXService.saveGpx(gpx2);
        List<GPX> expect = new ArrayList<>();
        expect.add(gpx3);
        List<GPXDTO> actual = gPXService.getLastedTrack(1);
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            CompareUtil.gPXMetadataWithDTO(expect.get(i), actual.get(i));
        });
    }

    @Test
    @Transactional
    public void getLastedTrack_outOfPage() {
        gPXService.saveGpx(gpx1);
        gPXService.saveGpx(gpx3);
        gPXService.saveGpx(gpx2);
        List<GPXDTO> actual = gPXService.getLastedTrack(2);
        assertTrue(CollectionUtils.isEmpty(actual));
    }

    @Test
    @Transactional
    public void getDetail_success() {
        GPXDTO gPXDTO2 = gPXService.saveGpx(gpx2);
        GPXDTO actual = gPXService.getDetail(gPXDTO2.getId());
        CompareUtil.gPXMetadataWithDTO(gpx2, actual);
        CompareUtil.gPXLinksWithDTO(gpx2.getMetadata().get().getLinks(), actual.getLinks());
        CompareUtil.gPXWayPointsWithDTO(gpx2.getWayPoints(), actual.getWaypoints());
        CompareUtil.gPXTracksWithDTO(gpx2.getTracks(), actual.getTracks());
    }

    @Test(expected = BusinessException.class)
    public void getDetail_fail() {
        GPXDTO gPXDTO2 = gPXService.saveGpx(gpx2);
        gPXService.getDetail(gPXDTO2.getId() + 10);
    }

}
