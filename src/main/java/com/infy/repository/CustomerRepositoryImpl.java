package com.infy.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.infy.dto.CustomerDTO;

//@Transactional annotation can also be placed at class level, which will make all its methods execute in a transaction scope.
//timeout transaction in 10 seconds
@Transactional(timeout = 60)
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	 //named parameters :name
	@Override
	public int createCustomer(CustomerDTO customer) {
		
		String query = "INSERT INTO TBL_CUSTOMER(phoneNo,name,email,address) Values (:phoneNo,:name,:email,:address)";
		
		
		SqlParameterSource namedParam = new MapSqlParameterSource("phoneNo", customer.getPhoneNo())
				.addValue("name", customer.getName())
				.addValue("email", customer.getEmail())
				.addValue("address", customer.getAddress());
				
		return namedParameterJdbcTemplate.update(query, namedParam);
		
	}
	@Override
	public int deleteCustomer(long id) {
		return jdbctemplate.update("delete from TBL_CUSTOMER where id = ? ",
				id);
	}
	
	// use of @Transactional at method level overrides class level annotation 
	//The readOnly attribute specifies that the transaction will only read data from a database.
	@Override
	@Transactional(readOnly = true) 
	public List<CustomerDTO> fetchCustomer() {
		String sql = "select id, phoneNo, name ,email, address from TBL_CUSTOMER";
		List<CustomerDTO> list= jdbctemplate.query(sql, new RowMapper<CustomerDTO>() {
			public CustomerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CustomerDTO cust = new CustomerDTO();
				cust.setId(rs.getLong("id"));
				cust.setPhoneNo(rs.getString("phoneNo"));
				cust.setName(rs.getString("name"));
				cust.setEmail(rs.getString("email"));
				cust.setAddress(rs.getString("address"));
				
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
