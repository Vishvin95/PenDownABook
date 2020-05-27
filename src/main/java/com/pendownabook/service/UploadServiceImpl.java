package com.pendownabook.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pendownabook.property.FileStorageProperties;

@Service
public class UploadServiceImpl implements UploadService {

	private final static Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
	private final Path fileStorageLocation;

	@Autowired
	public UploadServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String savePDF(MultipartFile previewBook, String uploadObject, String email) throws IOException {
		if (uploadObject.equals("PreviewBook")) {
			if (previewBook.isEmpty()) {
				System.out.println("Not found");
				return " ";
			} else {
				Path targetLocation = this.fileStorageLocation.resolve(previewBook.getOriginalFilename());
				Files.copy(previewBook.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
				logger.info("Preview Book Uploaded");
				return previewBook.getOriginalFilename();
			}
		} else {
			return " ";
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
            	logger.error("File not found " + fileName);
            	return null;            
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
	}
}
