package com.infy.service;

import java.io.*;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;


import com.infy.repository.FileRepository;


@Service("fileService")
public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileRepository fileRepository;
     
//	private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	
    public  int saveFile(String fileName,
            MultipartFile multipartFile) throws IOException {
    	
    	byte[] ImageInByte = multipartFile.getBytes();
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
    
    
	public  byte[] getFile(String fileName) throws IOException {
		return fileRepository.getFile(fileName);
//		ClassPathResource imgFile = new ClassPathResource("photos/"+fileName);
//		byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//		return bytes;
		
	}
}