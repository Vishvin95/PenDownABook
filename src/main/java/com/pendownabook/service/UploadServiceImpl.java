package com.pendownabook.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {

	@Override
	public String savePDF(MultipartFile previewBook, String uploadObject, String email) throws IOException {
		if (uploadObject.equals("PreviewBook")) {
			
			if(previewBook.isEmpty())
			{
				System.out.println("Not found");
				return " ";
			}
			else
			{
				Path path = Paths.get("previewbooks/" + previewBook.getOriginalFilename());
				Files.copy(previewBook.getInputStream(), path,  StandardCopyOption.REPLACE_EXISTING);
				return path.toString();
			}
		} else {
			return " ";
		}
	}

}
