package com.infy.repository;




public interface FileRepository {

	public  byte[] getFile(String filename);
	public int saveFile(String filename, byte[] filedata);
	public int deleteFile(String filename);
	public int updateFile(String filename, byte[] filedata);
	
}
