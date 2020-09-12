package com.revature;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

import com.revature.dao.UserDAO;
import com.revature.service.AccountService;
import com.revature.service.UserService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.entity.Account;
import com.revature.entity.User;

import io.restassured.http.ContentType;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class H2DemoApplicationTests {
    private static boolean initialized = false;
    private static int numTests = 0;
    @LocalServerPort
    private int port;
    @Autowired
    private AccountService as;

    @Autowired
    private UserService us;

    @BeforeEach
    void setUp() {
        if (!initialized) {
            User test = new User();
            test.setLastName("test");
            test.setFirstName("testy");
            test.setEmail("test");
            test.setPassword("tes");
            test.setAdmin(true);
            us.addUser(test);
            Account tes = new Account();
            tes.setAccTypeId(1);
            tes.setUserId(us.findUserByEmail("test").getUserId());
            tes.setPoints(25);
            as.addAccount(tes);
            initialized = true;
            System.out.println(us.getAllUsers());
        }
    }

    @AfterEach
    void tearDown() {
        numTests++;
        if (numTests == 12) {
            System.out.println("reached the end going to start cleaning");
            User userTBR = us.findUserByEmail("test@test.com");
            if (userTBR != null) {
                us.deleteUser(userTBR);
            }
            System.out.println(as.getAllUsers());
            List<Account> atbr = as.findAccountById(us.findUserByEmail("test").getUserId());
            atbr.forEach(account -> as.deleteAccount(account));
            User utbr = us.findUserByEmail("test");
            us.deleteUser(utbr);
        }
    }

    @Test
	void testUserControllerGetAllUsers() {
        System.out.println(port);
		//will result in failure if size() is different from the size() of the actual database
        int size = us.getAllUsers().size();
		get("http://localhost:" + port + "/user/all")
				.then()
				.assertThat()
				.statusCode(200)
				.body("size()", is(size));
	}


    @Test
    void testUserControllerGetUserById() {
        User u = us.findUserByEmail("test");
        given()
        	.contentType(ContentType.JSON)
            .body(u)
            .post("http://localhost:" + port + "/user/user")
            .then().statusCode(200).extract().response();
    }

    @Test
    void testUserControllerAddNewUser() {
	    User newUser = new User("test@test.com", "newTestPassword");
        given()
            .contentType(ContentType.JSON)
            .body(newUser)
            .post("http://localhost:" + port + "/user/new")
            .then().statusCode(200).extract().response()
            .then().assertThat().body("email",is(newUser.getEmail()));
    }

    @Test
    void testUserControllerUserLogin() {
	    User user = us.findUserByEmail("test");
        System.out.println(user);
        given()
            .contentType(ContentType.JSON)
            .body(user)
            .post("http://localhost:" + port + "/user/login")
            .then().statusCode(200).extract().response();
    }

    @Test
    void testUserControllerUpdateUser() {
	    User user = us.findUserByEmail("test");
        System.out.println(user);
        user.setFirstName("Jay");
        given()
            .contentType(ContentType.JSON)
            .body(user)
            .post("http://localhost:" + port + "/user/info")
            .then().statusCode(200).extract().response();
        System.out.println(user);
    }

    @Test
    void testUserControllerUpdatePassword() {
        User user = us.findUserByEmail("test");
        user.setPassword("tired123");
        given()
            .contentType(ContentType.JSON)
            .body(user)
            .post("http://localhost:" + port + "/user/cred")
            .then().statusCode(200).extract().response();
        System.out.println(us.findUserByEmail("test"));
    }

    @Test
    void testAccountControllerUpdatePoints() {
        Account a = as.findAccountById(us.findUserByEmail("test").getUserId()).get(0);
        a.setPoints(10);
        given()
            .contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:" + port + "/account/points")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(10);
    }
    @Test
    void testAccountControllerAddPoints() {
        Account a = as.findAccountById(us.findUserByEmail("test").getUserId()).get(0);
        a.setPoints(a.getPoints() + 10);
        int newPoints = a.getPoints();
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:" + port + "/account/points/a")
            .then().statusCode(200).extract().response();
        assertThat(a.getPoints()).isEqualTo(newPoints);
    }
    
    @Test
    void testAccountControllerAddAccount() {
    	Account a = new Account();
    	a.setUserId(1);
    	a.setAccTypeId(1);
    	a.setPoints(50);
        given()
        	.contentType(ContentType.JSON)
            .body(a)
            .post("http://localhost:" + port + "/account/account")
            .then().statusCode(200).extract().response();
    }
    
    @Test
    void testAccountControllerGetAccountById() {
    	Account a = as.findById(1);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a)
    		.post("http://localhost:" + port + "/account/account")
    		.then().statusCode(200).extract().response()
            .then().assertThat().body("accId", is(a.getAccId()));
    }
    
    @Test
    void testAccountControllerGetAccountByUserId() {
    	List<Account> a = as.findAccountById(us.findUserByEmail("test").getUserId());
        System.out.println(a);
    	given()
    		.contentType(ContentType.JSON)
    		.body(a.get(0))
    		.post("http://localhost:" + port + "/account/accounts")
    		.then().statusCode(200).extract().response();
    }
    
	@Test
	void contextLoads() {
	}

}
