package com.example.demo.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.Constant;
import com.example.demo.exception.FileStorageException;
import com.example.demo.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    /**
     * Store file in local folder
     */
    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains(Constant.DOUBLE_POINT)) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence.");
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = Paths.get(Constant.SAMPLE_FOLDER).toAbsolutePath().normalize().resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return targetLocation.toString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file. Please try again!", ex);
        }
    }
}
