package com.infy.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public  int saveFile(String fileName,MultipartFile multipartFile) throws IOException ;
	public  byte[] getFile(String fileName) throws IOException;
}
