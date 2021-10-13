package com.infy.repository;

import java.io.ByteArrayInputStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Repository;

import com.infy.dto.FileDTO;


@Repository("fileRepository")
public class FileRepositoryImpl implements FileRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private static Logger log = LoggerFactory.getLogger(FileRepository.class);
	
	@Override
	public int saveFile(String fileName, byte[] filedata){
		
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("filename", fileName);
		in.addValue("filedata",new SqlLobValue(new ByteArrayInputStream(filedata),filedata.length,new DefaultLobHandler()),Types.BLOB);
		String query = "INSERT INTO TBL_FILE(filename,filedata) Values (:filename,:filedata)";
		return namedParameterJdbcTemplate.update(query, in);

	}

	@Override
	public byte[] getFile(String filename) {
		String sql = "select filename,filedata from TBL_FILE where filename=:filename";
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("filename", filename);
		FileDTO file= namedParameterJdbcTemplate.queryForObject(sql,in, new RowMapper<FileDTO>() {
			public FileDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileDTO file = new FileDTO();
				file.setFilename(rs.getString("filename"));
				file.setFiledata(rs.getBytes("filedata"));
				return file;
			}
		});
		
		return file.getFiledata();
	}
	
	
	@Override
	public int deleteFile(String filename) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("filename", filename);
		String  query = "delete from TBL_FILE where filename = :filename ";
		return namedParameterJdbcTemplate.update(query,in);
	}

	@Override
	public int updateFile(String filename, byte[] filedata) {
		
		if(isFileExist(filename)>0) {
		log.info("File exist so update");
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("filename", filename);
		in.addValue("filedata",new SqlLobValue(new ByteArrayInputStream(filedata),filedata.length,new DefaultLobHandler()),Types.BLOB);
		String  query = "update TBL_FILE set filedata = :filedata   where filename = :filename";
		return namedParameterJdbcTemplate.update(query, in);
		}
		else {
			log.info("File does not exist so create new");
			return saveFile(filename,filedata);
			
		}
	}
	
	
	public int isFileExist(String filename)  {
		String sql = "select count(filename) from TBL_FILE where filename=:filename";
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("filename", filename);
		int count= namedParameterJdbcTemplate.queryForObject(sql,in,Integer.class);
		log.info("File Exists Response: "+ count);
		return count;
	}
	
	
}
