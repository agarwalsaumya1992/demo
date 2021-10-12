package com.infy.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerServiceImpl;

//@Transactional annotation can also be placed at class level, which will make all its methods execute in a transaction scope.
//timeout transaction in 10 seconds
@Transactional(timeout = 60)
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private FileRepository fileRepository;
	
	private static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	 //named parameters :name
	@Override
	public int createCustomer(CustomerDTO customer) {
		
		String query = "INSERT INTO TBL_CUSTOMER(phoneNo,name,email,address,photo) Values (:phoneNo,:name,:email,:address,:photo)";
		
		
		SqlParameterSource namedParam = new MapSqlParameterSource("phoneNo", customer.getPhoneNo())
				.addValue("name", customer.getName())
				.addValue("email", customer.getEmail())
				.addValue("address", customer.getAddress())
				.addValue("photo", customer.getPhoto());
				
		return namedParameterJdbcTemplate.update(query, namedParam);
		
	}
	@Override
	public int deleteCustomer(long id) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", id);
		String filename=namedParameterJdbcTemplate.queryForObject("select photo from TBL_CUSTOMER where id = :id",in,String.class);
		log.info(filename);
		if(filename!=null) {
		
			int n=	fileRepository.deleteFile(filename);
			log.info("file delete response: "+ n);
		}
		return namedParameterJdbcTemplate.update("delete from TBL_CUSTOMER where id = :id",
				in);
	}
	
	// use of @Transactional at method level overrides class level annotation 
	//The readOnly attribute specifies that the transaction will only read data from a database.
	@Override
	@Transactional(readOnly = true) 
	public List<CustomerDTO> fetchCustomer() {
		String sql = "select id, phoneNo, name ,email, address ,photo from TBL_CUSTOMER";
		List<CustomerDTO> list= jdbctemplate.query(sql, new RowMapper<CustomerDTO>() {
			public CustomerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CustomerDTO cust = new CustomerDTO();
				cust.setId(rs.getLong("id"));
				cust.setPhoneNo(rs.getString("phoneNo"));
				cust.setName(rs.getString("name"));
				cust.setEmail(rs.getString("email"));
				cust.setAddress(rs.getString("address"));
				cust.setPhoto(rs.getString("photo"));
				
				return cust;
			}
		});
		
		return list;
		
	}
	
	@Override
	public int updateCustomer(long id, CustomerDTO cust) {
		String query = "update TBL_CUSTOMER set phoneNo = ? , name = ? , email = ? , address = ?  where id = ?";
		return jdbctemplate.update(query, cust.getPhoneNo(), cust.getName(),cust.getEmail(),cust.getAddress(),id);
	}
}
