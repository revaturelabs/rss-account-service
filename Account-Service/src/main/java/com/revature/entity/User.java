package com.revature.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class User {

	
	
    @Column(name = "U_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_PK_SEQ")
    private int userId;
    
    @Column(name = "U_EMAIL", unique = true)
	private String email;
    
    @Column(name = "U_PASSWORD")
	private String password;
    
    @Column(name = "U_PIC", nullable = true, columnDefinition = "BLOB")
    private byte[] profilePic;
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name = "IS_ADMIN", columnDefinition = "boolean default false")
    private boolean isAdmin;

    
    
	public User() {
		super();
	}

	

	public User(int userId, String email, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public User(int userId, String email, String password, byte[] profilePic, String firstName, String lastName,
			boolean isAdmin) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

	

	public User(int userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(byte[] profilePic) {
		this.profilePic = profilePic;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", email=" + email + ", password=" + password + ", profilePic="
				+ Arrays.toString(profilePic) + ", firstName=" + firstName + ", lastName=" + lastName + ", isAdmin="
				+ isAdmin + "]";
	}

	
	
}
