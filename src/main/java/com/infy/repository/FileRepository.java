package com.infy.repository;

import java.io.IOException;
import java.util.List;

import com.infy.dto.FileDTO;


public interface FileRepository {

	public  byte[] getFile(String fileName);
	public int saveFile(String fileName, byte[] filedata);
	public int isFileExist(String filename);
	
}
