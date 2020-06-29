package com.revature;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.entity.Account;
import com.revature.entity.User;

import io.restassured.http.ContentType;


class H2DemoApplicationTests {
	
	@Test
	public void testUserControllerGetAllUsers() {
		//will result in failure if size() is different from the size() of the actual database
		get("http://localhost:9000/user/all")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()", is(2));
	}
	
    @Test
    public void testUserControllerGetUserById() {
        User u = new User();
        u.setUserId(1);
        given()
        	.contentType(ContentType.JSON)
            .body(u)
            .post("http://localhost:9000/user/user")
            .then().statusCode(200).extract().response();
    }
	
    @Test
    public void testAccountControllerUpdatePoints() {
        Account a = new Account();
        a.setAccId(1);
        a.setPoints(10);
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:9000/account/points")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(10);
    }
    
    @Test
    public void testAccountControllerAddPoints() {
        Account a = new Account();
        a.setAccId(1);
        a.setPoints(10);
        a.setPoints(a.getPoints() + 10);
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:9000/account/points/a")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(20);
    }
    
    @Test
    public void testAccountControllerAddAccount() {
    	Account a = new Account();
    	a.setUserId(1);
    	a.setAccTypeId(1);
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:9000/account/account")
            .then().statusCode(200).extract().response();
    }
    
    @Test
    public void testAccountControllerGetAccountById() {
    	Account a = new Account();
    	a.setAccId(3);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a)
    		.get("http://localhost:9000/account/account/ai")
    		.then().statusCode(200).extract().response();
    }
    
    @Test
    public void testAccountControllerGetAccountByUserId() {
    	Account a = new Account();
    	a.setUserId(1);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a)
    		.get("http://localhost:9000/account/account/ui")
    		.then().statusCode(200).extract().response();
    }
    
	@Test
	void contextLoads() {
	}

}
