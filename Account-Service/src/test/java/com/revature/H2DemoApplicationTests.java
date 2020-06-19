package com.revature;

import static org.hamcrest.CoreMatchers.is;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.get;

import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class H2DemoApplicationTests {
	
	@Test
	public void testUserControllerGetAllUsers() {

		get("http://localhost:9000/user/all")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()", is(1));
	}
	@Test
	void contextLoads() {
	}

}
