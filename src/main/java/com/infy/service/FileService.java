package com.infy.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.infy.exceptions.NoSuchRecordException;

public interface FileService {

	public  int saveFile(String filename,MultipartFile file) throws IOException ;
	public  byte[] getFile(String filename) throws IOException;
	public int updateFile(String filename, MultipartFile file) throws IOException;
	public String deleteFile(String filename) throws NoSuchRecordException;
}
