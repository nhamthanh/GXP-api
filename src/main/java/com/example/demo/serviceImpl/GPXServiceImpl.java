package com.example.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.converter.GPXConverter;
import com.example.demo.converter.LinkConverter;
import com.example.demo.dto.GPXDTO;
import com.example.demo.entity.GPXEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.respository.GpxRespository;
import com.example.demo.service.GPXService;
import com.example.demo.service.TrackService;
import com.example.demo.service.WayPointService;

import io.jenetics.jpx.GPX;

@Service
public class GPXServiceImpl implements GPXService {

    @Autowired
    GpxRespository gpxRespository;

    @Autowired
    WayPointService wayPointService;

    @Autowired
    TrackService trackService;

    @Autowired
    private Environment env;

    /**
     * persist gpx information
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public GPXDTO saveGpx(GPX gpx) {
        // create gpx model
        GPXEntity gpxEntity = new GPXEntity();
        GPXConverter.gpxToEntity(gpx, gpxEntity);
        // Create link data
        LinkConverter.gpxToEntity(gpx.getMetadata().get().getLinks(), gpxEntity);
        // Create tracks data
        trackService.createTrack(gpx.getTracks(), gpxEntity);
        // Create WayPoints data
        wayPointService.createWayPoints(gpx.getWayPoints(), gpxEntity);
        // Persist gpx
        GPXEntity result = gpxRespository.save(gpxEntity);
        GPXDTO resultDTO = new GPXDTO();
        GPXConverter.entityToDTOFullLoad(result, resultDTO);
        return resultDTO;
    }

    /**
     * Get lasted track information list
     */
    @Override
    public List<GPXDTO> getLastedTrack(int page) {
        PageRequest request = new PageRequest(page, Integer.valueOf(env.getProperty("spring.num-per-page")),
                Sort.Direction.DESC, "time");
        Page<GPXEntity> gPXEntities = gpxRespository.findAll(request);
        List<GPXDTO> gPXDTOs = new ArrayList<>();
        GPXConverter.gpxsToDTOs(gPXEntities.getContent(), gPXDTOs);
        return gPXDTOs;
    }

    /**
     * Get detail of gpx information by id
     * 
     * @throws Exception
     */
    @Override
    public GPXDTO getDetail(long id) throws BusinessException {
        GPXDTO gPXDTO = new GPXDTO();
        GPXEntity result = gpxRespository.findOne(id);
        if (result == null) {
            throw new BusinessException("GPX is not exist");
        }
        GPXConverter.entityToDTOFullLoad(result, gPXDTO);
        return gPXDTO;
    }
}
