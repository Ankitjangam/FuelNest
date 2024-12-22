package com.FuelNest.Application;

import com.FuelNest.Application.Model.Admin;
import com.FuelNest.Application.Repository.AdminRepository;
import com.FuelNest.Application.Service.AdminService;
import com.FuelNest.Application.Controller.AdminController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdminControllerTest {

	@Autowired
	private AdminController adminController;

	@Autowired
	private AdminService adminService;

	@Autowired
	private AdminRepository adminRepository;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}

	@Test
	public void testRegisterAdmin() throws Exception {
		String json = "{\n" +
				"\"firstname\": \"Ankit\",\n" +
				"\"lastname\": \"Jangam\",\n" +
				"\"password\": \"examplePassword123\",\n" +
				"\"confirmPassword\": \"examplePassword123\",\n" +
				"\"governmentId\": \"Aadhar\",\n" +
				"\"id_no\": \"1234-5678-9012\",\n" +
				"\"address\": \"123 Main Street\",\n" +
				"\"country\": \"India\",\n" +
				"\"state\": \"Karnataka\",\n" +
				"\"district\": \"Raichur\",\n" +
				"\"city\": \"Raichur\",\n" +
				"\"pin_code\": \"123456\",\n" +
				"\"address_line1\": \"Main Street, XYZ\"\n" +
				"}";

		mockMvc.perform(post("/admin/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk()); // Expect a 200 OK response
	}
}
