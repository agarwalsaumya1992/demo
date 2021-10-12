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

import com.infy.dto.ProductDTO;
import com.infy.service.ProductServiceImpl;

//@Transactional annotation can also be placed at class level, which will make all its methods execute in a transaction scope.
//timeout transaction in 10 seconds
@Transactional(timeout = 60)
@Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private FileRepository fileRepository;
	
	private static Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	 //named parameters :name
	@Override
	public int createProduct(ProductDTO product) {
		
		String query = "INSERT INTO TBL_PRODUCT(category,name,price,quantity,photo) Values (:category,:name,:price,:quantity,:photo)";
		
		
		SqlParameterSource namedParam = new MapSqlParameterSource("category", product.getCategory())
				.addValue("name", product.getName())
				.addValue("price", product.getPrice())
				.addValue("quantity", product.getQuantity())
				.addValue("photo", product.getPhoto());
				
		return namedParameterJdbcTemplate.update(query, namedParam);
		
	}
	@Override
	public int deleteProduct(long id) {
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("id", id);
		String filename=namedParameterJdbcTemplate.queryForObject("select photo from TBL_PRODUCT where id = :id",in,String.class);
		log.info(filename);
		if(filename!=null) {
		
			int n=	fileRepository.deleteFile(filename);
			log.info("file delete response: "+ n);
		}
		return namedParameterJdbcTemplate.update("delete from TBL_PRODUCT where id = :id",in);
	}
	
	// use of @Transactional at method level overrides class level annotation 
	//The readOnly attribute specifies that the transaction will only read data from a database.
	@Override
	@Transactional(readOnly = true) 
	public List<ProductDTO> fetchProduct() {
		String sql = "select id, category, name ,price, quantity ,photo from TBL_PRODUCT";
		List<ProductDTO> list= jdbctemplate.query(sql, new RowMapper<ProductDTO>() {
			public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductDTO prod = new ProductDTO();
				prod.setId(rs.getLong("id"));
				prod.setCategory(rs.getString("category"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setQuantity(rs.getInt("quantity"));
				prod.setPhoto(rs.getString("photo"));
				
				return prod;
			}
		});
		
		return list;
		
	}
	
	@Override
	public int updateProduct(ProductDTO prod) {
		String query = "update TBL_PRODUCT set category = ? , name = ? , price = ? , quantity = ?  where id = ?";
		return jdbctemplate.update(query, prod.getCategory(), prod.getName(),prod.getPrice(),prod.getQuantity(),prod.getId());
	}
}
