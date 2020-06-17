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
    private Long id;
    
    @Column(name = "U_EMAIL", unique = true, nullable = false)
	private String email;
    
    @Column(name = "U_PASSWORD", nullable = false)
	private String password;
    
    @Column(name = "U_PIC", nullable = true)
    private byte[] profilePic;
    
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    
    @Column(name = "IS_ADMIN", columnDefinition = "boolean default false")
    private boolean isAdmin;

	public User() {
		super();
	}

	public User(String email, String password, byte[] profilePic, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(Long id, String email, String password, byte[] profilePic, String firstName, String lastName,
			boolean isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

	public User(String email, String password, byte[] profilePic, String firstName, String lastName, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", profilePic="
				+ Arrays.toString(profilePic) + ", firstName=" + firstName + ", lastName=" + lastName + ", isAdmin="
				+ isAdmin + "]";
	}

	
	
}
