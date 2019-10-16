package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.constant.Constant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileStorageServiceTest {

    private MockMultipartFile file, file2;

    @Autowired
    private FileStorageService fileStorageService;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException, IOException {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("testFile/testUpload1.gpx");
        file = new MockMultipartFile(Constant.FILE, "testUpload1.gpx", MediaType.MULTIPART_FORM_DATA_VALUE,
                inputStream);
        file2 = new MockMultipartFile(Constant.FILE, "testUpload2..gpx", MediaType.MULTIPART_FORM_DATA_VALUE,
                inputStream);
    }

    @Test
    public void storeFileTest_success() {
        String pathString = fileStorageService.storeFile(file);
        // get file name from path
        Path path = Paths.get(pathString);
        Path fileName = path.getFileName();
        assertEquals("testUpload1.gpx", String.valueOf(fileName));
    }

    @Test(expected = Exception.class)
    public void storeFileTest_exception() {
        fileStorageService.storeFile(file2);
    }
}
