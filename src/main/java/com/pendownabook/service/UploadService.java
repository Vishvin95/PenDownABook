package com.pendownabook.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public String savePDF(MultipartFile previewBook, String uploadObject, String email) throws IOException;

}
