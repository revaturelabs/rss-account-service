package com.revature;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.revature.dao.UserDAO;
import com.revature.service.AccountService;
import com.revature.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.entity.Account;
import com.revature.entity.User;

import io.restassured.http.ContentType;

import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class H2DemoApplicationTests {
    @Autowired
    private AccountService as;

    @Autowired
    private UserService us;

	
	@Test
	void testUserControllerGetAllUsers() {
		//will result in failure if size() is different from the size() of the actual database
        int size = us.getAllUsers().size();
		get("http://localhost:9000/user/all")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()", is(size));
	}


    @Test
    void testUserControllerGetUserById() {
        User u = us.findById(1);
        given()
        	.contentType(ContentType.JSON)
            .body(u)
            .post("http://localhost:9000/user/user")
            .then().statusCode(200).extract().response();
    }

    @Test
    void testUserControllerAddNewUser() {
	    User newUser = new User("test@test.com", "newTestPassword");
        given()
            .contentType(ContentType.JSON)
            .body(newUser)
            .post("http://localhost:9000/user/new")
            .then().statusCode(200).extract().response()
            .then().assertThat().body("email",is(newUser.getEmail()));
        User userTBRR = us.findUserByEmail(newUser.getEmail());
        if (userTBRR != null) {
            us.deleteUser(userTBRR);
        }
    }

    @Test
    void testUserControllerUserLogin() {
	    User user = us.findById(1);
        System.out.println(user);
        given()
            .contentType(ContentType.JSON)
            .body(user)
            .post("http://localhost:9000/user/login")
            .then().statusCode(200).extract().response();
    }

    @Test
    void testAccountControllerUpdatePoints() {
        Account a = as.findById(1);
        a.setPoints(10);
        given()
            .contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:9000/account/points")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(10);
    }
    @Test
    void testAccountControllerAddPoints() {
        Account a = as.findById(1);
        a.setPoints(a.getPoints() + 10);
        int newPoints = a.getPoints();
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:9000/account/points/a")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(newPoints);
    }
    
    @Test
    void testAccountControllerAddAccount() {
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
    void testAccountControllerGetAccountById() {
    	Account a = as.findById(1);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a)
    		.post("http://localhost:9000/account/account")
    		.then().statusCode(200).extract().response();
    }
    
    @Test
    void testAccountControllerGetAccountByUserId() {
    	List<Account> a = as.findAccountById(1);
        System.out.println(a);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a.get(0))
    		.post("http://localhost:9000/account/accounts")
    		.then().statusCode(200).extract().response();
    }
    
	@Test
	void contextLoads() {
	}

}
