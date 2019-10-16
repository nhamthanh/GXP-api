package com.example.demo.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import com.example.demo.dto.GPXDTO;
import com.example.demo.dto.LinkDTO;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.TrackDTO;
import com.example.demo.dto.TrackPointDTO;
import com.example.demo.dto.TrackSegmentDTO;
import com.example.demo.dto.WayPointDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.entity.LinkEntity;
import com.example.demo.entity.TrackEntity;
import com.example.demo.entity.WayPointEntity;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Link;
import io.jenetics.jpx.Person;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;

public class CompareUtil {
    public static void gPXMetadataWithDTO(GPX expect, GPXDTO actual) {
        assertEquals(expect.getMetadata().get().getName().get(), actual.getName());
        assertEquals(expect.getMetadata().get().getDescription().get(), actual.getDesc());
        assertEquals(expect.getMetadata().get().getTime().get(), actual.getTime());
        gPXAuthorsWithDTO(expect.getMetadata().get().getAuthor(), actual.getAuthor());
    }

    public static void metadataEntityWithDTO(GPXEntity expect, GPXDTO actual) {
        assertEquals(expect.getName(), actual.getName());
        assertEquals(expect.getDesc(), actual.getDesc());
        assertEquals(expect.getTime(), actual.getTime());
    }

    public static void gPXAuthorsWithDTO(Optional<Person> expect, PersonDTO actual) {
        if (expect.isPresent()) {
            assertEquals(expect.get().getName().orElse(null), actual.getName());
        } else {
            assertNull(actual);
        }
    }

    public static void gPXLinksWithDTO(List<Link> expect, List<LinkDTO> actual) {
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            assertEquals(expect.get(i).getText().get(), actual.get(i).getText());
            assertEquals(expect.get(i).getHref().toString(), actual.get(i).getHref());
        });
    }

    public static void linkEntitiesWithDTO(Set<LinkEntity> expects, List<LinkDTO> actuals) {
        assertEquals(expects.size(), actuals.size());
        int i = 0;
        for (LinkEntity expect : expects) {
            assertEquals(expect.getHref(), actuals.get(i).getHref());
            assertEquals(expect.getText(), actuals.get(i).getText());
            i++;
        }
    }

    public static void gPXLinksWithEntities(List<Link> expects, Set<LinkEntity> actuals) {
        assertEquals(expects.size(), actuals.size());
        int i = 0;
        for (LinkEntity actual : actuals) {
            assertEquals(String.valueOf(expects.get(i).getHref()), actual.getHref());
            assertEquals(expects.get(i).getText().orElse(null), actual.getText());
            i++;
        }
    }

    public static void gPXWayPointsWithDTO(List<WayPoint> expect, List<WayPointDTO> actual) {
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            assertEquals(String.valueOf(expect.get(i).getLatitude()), actual.get(i).getLat());
            assertEquals(String.valueOf(expect.get(i).getLongitude()), actual.get(i).getLon());
            assertEquals(expect.get(i).getName().orElse(null), actual.get(i).getName());
            assertEquals(expect.get(i).getSymbol().orElse(null), actual.get(i).getSym());
        });
    }

    public static void wayPointEntitiesWithDTO(Set<WayPointEntity> expect, List<WayPointDTO> actual) {
        assertEquals(expect.size(), actual.size());
        int i = 0;
        for (WayPointEntity entity : expect) {
            assertEquals(entity.getLat(), actual.get(i).getLat());
            assertEquals(entity.getLon(), actual.get(i).getLon());
            assertEquals(entity.getName(), actual.get(i).getName());
            assertEquals(entity.getSym(), actual.get(i).getSym());
            i++;
        }
    }

    public static void gPXWayPointsWithEntity(List<WayPoint> expects, Set<WayPointEntity> actuals) {
        int index = 0;
        for (WayPointEntity actual : actuals) {
            assertEquals(String.valueOf(expects.get(index).getLatitude()), actual.getLat());
            assertEquals(String.valueOf(expects.get(index).getLongitude()), actual.getLon());
            assertEquals(expects.get(index).getName().orElse(null), actual.getName());
            assertEquals(expects.get(index).getSymbol().orElse(null), actual.getSym());
            index++;
        }
    }

    public static void gPXTracksWithDTO(List<Track> expect, List<TrackDTO> actual) {
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            assertEquals(expect.get(i).getName().orElse(null), actual.get(i).getName());
            assertEquals(expect.get(i).getComment().orElse(null), actual.get(i).getComment());
            assertEquals(expect.get(i).getType().orElse(null), actual.get(i).getType());
            assertEquals(expect.get(i).getSource().orElse(null), actual.get(i).getSource());
            assertEquals(expect.get(i).getDescription().orElse(null), actual.get(i).getDescription());
            gPXSegmentsWithDTO(expect.get(i).getSegments(), actual.get(i).getTrackSegments());
        });
    }

    public static void gPXSegmentsWithDTO(List<TrackSegment> expect, List<TrackSegmentDTO> actual) {
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            gPXTrackPointsWithDTO(expect.get(i).getPoints(), actual.get(i).getTrackPoints());
        });
    }

    public static void gPXTrackPointsWithDTO(List<WayPoint> expect, List<TrackPointDTO> actual) {
        assertEquals(expect.size(), actual.size());
        IntStream.range(0, expect.size()).forEach(i -> {
            assertEquals(String.valueOf(expect.get(i).getLatitude()), actual.get(i).getLat());
            assertEquals(String.valueOf(expect.get(i).getLongitude()), actual.get(i).getLon());
            assertEquals(GPXUtils.lengthToString(expect.get(i).getElevation().orElse(null)), actual.get(i).getEle());
            assertEquals(expect.get(i).getTime().orElse(null), actual.get(i).getTime());
        });
    }

    public static void gPXTracksWithEntity(List<Track> expects, Set<TrackEntity> actuals) {
        int index = 0;
        for (TrackEntity actual : actuals) {
            assertEquals(expects.get(index).getComment().orElse(null), actual.getComment());
            assertEquals(expects.get(index).getName().orElse(null), actual.getName());
            assertEquals(expects.get(index).getDescription().orElse(null), actual.getDescription());
            assertEquals(expects.get(index).getType().orElse(null), actual.getType());
            index++;
        }
    }

    public static void trackEntitiesWithDTO(Set<TrackEntity> expect, List<TrackDTO> actual) {
        assertEquals(expect.size(), actual.size());
        int i = 0;
        for (TrackEntity entity : expect) {
            assertEquals(entity.getComment(), actual.get(i).getComment());
            assertEquals(entity.getDescription(), actual.get(i).getDescription());
            assertEquals(entity.getName(), actual.get(i).getName());
            assertEquals(entity.getType(), actual.get(i).getType());
            i++;
        }
    }

}
