package com.infy.repository;




public interface FileRepository {

	public  byte[] getFile(String fileName);
	public int saveFile(String fileName, byte[] filedata);
	public int isFileExist(String filename);
	public int deleteFile(String filename);
	
}
