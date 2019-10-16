package com.example.demo.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.constant.Constant;
import com.example.demo.dto.GPXDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.FileStorageException;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.GPXService;

import io.jenetics.jpx.GPX;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GpxControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GPXService service;

    @MockBean
    private FileStorageService fileStorageService;

    private MockMultipartFile file;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException, IOException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("testFile/testUpload1.gpx");
        file = new MockMultipartFile(Constant.FILE, "testUpload1.gpx", MediaType.MULTIPART_FORM_DATA_VALUE,
                inputStream);
    }

    @Test
    public void gpxUpdpload_success() throws Exception {
        GPXDTO gPXDTO = new GPXDTO();
        GPX gpx = GPX.read("sample/sample.gpx");
        given(fileStorageService.storeFile(file)).willReturn("sample/sample.gpx");
        given(service.saveGpx(gpx)).willReturn(gPXDTO);
        mvc.perform(MockMvcRequestBuilders.fileUpload("/gpx/uploadFile").file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA)).andExpect(status().isOk());
    }

    @Test
    public void gpxUpdpload_FileStorageException() throws Exception {
        GPXDTO gPXDTO = new GPXDTO();
        GPX gpx = GPX.read("sample/sample.gpx");
        given(fileStorageService.storeFile(file))
                .willThrow(new FileStorageException("Could not store file. Please try again!"));
        given(service.saveGpx(gpx)).willReturn(gPXDTO);
        mvc.perform(MockMvcRequestBuilders.fileUpload("/gpx/uploadFile").file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    public void getLastedTrack() throws Exception {
        GPXDTO gPXDTO = new GPXDTO();
        List<GPXDTO> gPXDTOs = Arrays.asList(gPXDTO);
        given(service.getLastedTrack(1)).willReturn(gPXDTOs);
        mvc.perform(get("/gpx/lastedTrack/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getDetail_success() throws Exception {
        GPXDTO gPXDTO = new GPXDTO();
        given(service.getDetail(1)).willReturn(gPXDTO);
        mvc.perform(get("/gpx/detail/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getDetail_exception() throws Exception {
        given(service.getDetail(100)).willThrow(new BusinessException("gpx is not exist"));
        mvc.perform(get("/gpx/detail/100").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
