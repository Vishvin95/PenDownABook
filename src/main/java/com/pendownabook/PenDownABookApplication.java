package com.pendownabook;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pendownabook.entities.Role;
import com.pendownabook.entities.Service;
import com.pendownabook.entities.User;
import com.pendownabook.service.CustomerService;
import com.pendownabook.service.RoleService;
import com.pendownabook.service.UserService;

@SpringBootApplication
public class PenDownABookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PenDownABookApplication.class, args);
	}

	@Component
	public class DatabaseLoader implements CommandLineRunner {
		private RoleService roleService;
		private CustomerService customerService;
		private UserService userService;
		
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		@Autowired
		public DatabaseLoader(RoleService roleService, CustomerService customerService, UserService userService) {
			this.roleService = roleService;
			this.customerService = customerService;
			this.userService = userService;
		}

		@Override
		public void run(String... args) throws Exception {
			try {
				HashSet<Role> roles = new HashSet<>();
				ArrayList<Service> services = new ArrayList<>();

				// -----------Loading roles------------//			
				Role role = roleService.getByName("USER");
				Role newRole=null;
				if (role == null) {
					newRole = new Role();
					newRole.setName("USER");
					roles.add(newRole);
				}

				role = roleService.getByName("PUBLISHER");
				if (role == null) {
					newRole = new Role();
					newRole.setName("PUBLISHER");
					roles.add(newRole);
				}
				
				role = roleService.getByName("ADMIN");
				if (role == null) {
					newRole = new Role();
					newRole.setName("ADMIN");
					roles.add(newRole);
				}
				roleService.saveAll(roles);

				// ----------Loading Services-------------//
				Service exist = customerService.getByServiceCode("S1"); 
				Service service;
				if ( exist == null) {
					service = new Service();
					service.setServiceCode("S1");
					service.setServiceTitle("Publishing Service");
					service.setServiceDescription("This is publishing service for three months");
					service.setServicePeriod(3);
					service.setServiceCost(800);
					services.add(service);
				}

				exist = customerService.getByServiceCode("S2"); 				
				if (exist == null) {
					service = new Service();
					service.setServiceCode("S2");
					service.setServiceTitle("Publishing Service");
					service.setServiceDescription("This is publishing service for six months");
					service.setServicePeriod(6);
					service.setServiceCost(1500);
					services.add(service);
				}
				
				exist = customerService.getByServiceCode("S3"); 				
				if (exist == null) {
					service = new Service();
					service.setServiceCode("S3");
					service.setServiceTitle("Publishing Service");
					service.setServiceDescription("This is publishing service for twelve months");
					service.setServicePeriod(12);
					service.setServiceCost(3000);
					services.add(service);
				}
				customerService.saveAll(services);
				
				//---------------Load admin user-----------------//			
				User admin = userService.findByEmail("admin@gmail.com");
				if(admin==null)
				{
					User user = new User();
					user.setFirstName("Admin");
					user.setLastName("Minto");
					user.setContact(9892990123l);
					user.setEmail("admin@gmail.com");
					user.setPassword(passwordEncoder.encode("admin"));																		
					user = userService.saveAdmin(user);
				}
				
			} catch (Exception e) {

			}
		}
	}
}
