package com.infy.service;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.infy.exceptions.NoSuchRecordException;
import com.infy.repository.FileRepository;
import com.infy.util.AppConstants;


@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private Environment environment;
     
	private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
    public  int saveFile(String fileName,MultipartFile file) throws IOException {
    	
    	byte[] ImageInByte= file.getBytes();
    	return fileRepository.saveFile(fileName, ImageInByte);
    	
//        Path uploadPath = Paths.get("src/main/resources/photos");
//         
//        if (!Files.exists(uploadPath)) {
//        	log.info("File directory does not exist so creating: "+uploadPath); 
//            Files.createDirectories(uploadPath);
//            
//        }
//         
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ioe) {        
//            throw new IOException("Could not save image file: " + fileName, ioe);
//        }      
    }
    
	@Override
	public  byte[] getFile(String fileName) throws IOException {
		return fileRepository.getFile(fileName);
//		ClassPathResource imgFile = new ClassPathResource("photos/"+fileName);
//		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//		return bytes;
		
	}


	@Override
	public int updateFile(String filename, MultipartFile file) throws IOException {
    	byte[] ImageInByte = file.getBytes();
    	return fileRepository.updateFile(filename, ImageInByte);
	}

	@Override
	public String deleteFile(String fileName) throws NoSuchRecordException {
		
		log.info("Begin file delete: "+fileName);
		int response = fileRepository.deleteFile(fileName);
		log.info("Delete file response: "+response);
		if(response!=1)
			throw new NoSuchRecordException(environment.getProperty(AppConstants.RECORD_NOT_FOUND.toString()));
		return environment.getProperty(AppConstants.DELETE_SUCCESS.toString());
		
		
	}
}