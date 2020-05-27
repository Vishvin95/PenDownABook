package com.pendownabook;

import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pendownabook.entities.ReviewStatus;
import com.pendownabook.entities.Role;
import com.pendownabook.entities.Service;
import com.pendownabook.entities.User;
import com.pendownabook.property.FileStorageProperties;
import com.pendownabook.service.CustomerService;
import com.pendownabook.service.ReviewService;
import com.pendownabook.service.ReviewStatusService;
import com.pendownabook.service.RoleService;
import com.pendownabook.service.UserService;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class PenDownABookApplication {

	public static void main(String[] args) {
		SpringApplication.run(PenDownABookApplication.class, args);
	}

	@Component
	public class DatabaseLoader implements CommandLineRunner {
		private RoleService roleService;
		private CustomerService customerService;
		private UserService userService;
		private ReviewStatusService reviewStatusService;

		@Autowired
		private BCryptPasswordEncoder passwordEncoder;

		@Autowired
		public DatabaseLoader(RoleService roleService, CustomerService customerService, UserService userService, ReviewStatusService reviewStatusService) {
			this.roleService = roleService;
			this.customerService = customerService;
			this.userService = userService;
			this.reviewStatusService = reviewStatusService;
		}

		@Override
		public void run(String... args) throws Exception {
			try {
				HashSet<Role> roles = new HashSet<>();
				ArrayList<Service> services = new ArrayList<>();
				ArrayList<ReviewStatus> reviewStatuses = new ArrayList<>();

				// -----------Loading roles------------//
				Role role = roleService.getByName("USER");
				Role newRole = null;
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
				
				// -----------Loading review status------------//
				ReviewStatus reviewStatus = reviewStatusService.getByName("UPLOADED");
				ReviewStatus newReviewStatus = null;
				if (reviewStatus == null) {
					newReviewStatus = new ReviewStatus();
					newReviewStatus.setName("UPLOADED");
					newReviewStatus.setDescription("Preview Book Has Been uploaded");
					reviewStatuses.add(newReviewStatus);
				}
				
				reviewStatus = reviewStatusService.getByName("IN REVIEW");
				if (reviewStatus == null) {
					newReviewStatus = new ReviewStatus();
					newReviewStatus.setName("IN PROGRESS");
					newReviewStatus.setDescription("Preview Book is under review by publisher");
					reviewStatuses.add(newReviewStatus);
				}
				
				reviewStatus = reviewStatusService.getByName("ACCEPTED");
				if (reviewStatus == null) {
					newReviewStatus = new ReviewStatus();
					newReviewStatus.setName("ACCEPTED");
					newReviewStatus.setDescription("Preview Book Has Been accepted");
					reviewStatuses.add(newReviewStatus);
				}
				
				reviewStatus = reviewStatusService.getByName("REJECTED");
				if (reviewStatus == null) {
					newReviewStatus = new ReviewStatus();
					newReviewStatus.setName("REJECTED");
					newReviewStatus.setDescription("Preview Book Has Been rejected");
					reviewStatuses.add(newReviewStatus);
				}

				reviewStatusService.saveAll(reviewStatuses);
				

				// ----------Loading Services-------------//
				Service exist = customerService.getByServiceCode("S1");
				Service service;
				if (exist == null) {
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

				// ---------------Load admin user-----------------//
				User admin = userService.findByEmail("admin@gmail.com");
				if (admin == null) {
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
