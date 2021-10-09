package com.infy.repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.infy.dto.CustomerDTO;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	@Override
	public int createCustomer(CustomerDTO customer) {
		
		String query = "INSERT INTO TBL_CUSTOMER(phoneNo,name,email,address) Values (?,?,?,?)";
		
		return jdbctemplate.update(query,customer.getPhoneNo(),customer.getName(),customer.getEmail(),customer.getAddress());
		
	}
	@Override
	public int deleteCustomer(Long phoneNo) {
		return jdbctemplate.update("delete from TBL_CUSTOMER where phoneNo = ? ",
				phoneNo);
	}
	@Override
	public List<CustomerDTO> fetchCustomer() {
		String sql = "select phoneNo, name ,email, address from TBL_CUSTOMER";
		return jdbctemplate.query(sql, new RowMapper<CustomerDTO>() {
			public CustomerDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CustomerDTO cust = new CustomerDTO();
				cust.setPhoneNo(rs.getLong("phoneNo"));
				cust.setName(rs.getString("name"));
				cust.setEmail(rs.getString("email"));
				cust.setAddress(rs.getString("address"));
				
				return cust;
			}
		});
	}
	
	@Override
	public int updateCustomer(long phoneNumber, CustomerDTO cust) {
		String query = "update TBL_CUSTOMER set phoneNo = ? , name = ? , email = ? , address = ?  where phoneNo = ?";
		return jdbctemplate.update(query, cust.getPhoneNo(), cust.getName(),cust.getEmail(),cust.getAddress(),phoneNumber);
	}
}
