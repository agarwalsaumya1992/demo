package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		
		
		SpringApplication.run(DemoApplication.class, args);
//		CustomerServiceImpl service = null;
//		AbstractApplicationContext context = (AbstractApplicationContext) SpringApplication.run(DemoApplication.class,
//				args);
//		service = (CustomerServiceImpl) context.getBean("customerService");
//		List<CustomerDTO> listcust = service.fetchCustomer();
//		System.out.println("PhoneNumer" + "   " + "Name" + "   " + "Email" + "     " + "Address");
//		for (CustomerDTO customerDTO2 : listcust) {
//			System.out.format("%5d%10s%20s%10s", customerDTO2.getPhoneNo(), customerDTO2.getName(),
//					customerDTO2.getEmail(), customerDTO2.getAddress());
//			System.out.println();
//		}
	}
	
}
