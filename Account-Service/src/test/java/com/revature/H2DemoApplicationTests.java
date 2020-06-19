package com.revature;

import static org.hamcrest.CoreMatchers.is;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.entity.User;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

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
    public void testUserControllerGetUserById() {
        User u = new User();
        u.setUserId(1);
        given()
        	.contentType(ContentType.JSON)
            .body(u)
            .post("http://localhost:9000/user/getuserbyid")
            .then().statusCode(200).extract().response();
    }
	
    
    
	@Test
	void contextLoads() {
	}

}
