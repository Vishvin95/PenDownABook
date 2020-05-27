package com.pendownabook.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public String savePDF(MultipartFile previewBook, String uploadObject, String email) throws IOException;

	public Resource loadFileAsResource(String fileName);

}
