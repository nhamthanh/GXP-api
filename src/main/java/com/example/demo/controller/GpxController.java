package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.GPXDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.GPXService;

import io.jenetics.jpx.GPX;

@RestController
@RequestMapping(value = "/gpx")
public class GpxController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GPXService gPXService;

    @PostMapping("/uploadFile")
    public ResponseEntity<GPXDTO> gpxUpdpload(@RequestParam("file") MultipartFile file) {
        GPXDTO persistedResult = null;
        try {
            String filePath = fileStorageService.storeFile(file);
            final GPX gpx = GPX.read(filePath);
            persistedResult = gPXService.saveGpx(gpx);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(persistedResult, HttpStatus.OK);
    }

    @GetMapping("/lastedTrack/{page}")
    public ResponseEntity<List<GPXDTO>> getLastedTrack(@Valid @NotNull @PathVariable int page) {
        return new ResponseEntity<>(gPXService.getLastedTrack(page), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<GPXDTO> getDetail(@Valid @NotNull @PathVariable Long id) {
        try {
            return new ResponseEntity<>(gPXService.getDetail(id), HttpStatus.OK);
        } catch (BusinessException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
