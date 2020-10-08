package com.revature.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@Column(name="user_discounted")
    private Boolean userDiscounted;
    
    @Column(name="user_discount")
    private Integer userDiscount;
    
    public Boolean isUserDiscounted() {
		return userDiscounted;
	}

	public void setUserDiscounted(Boolean userDiscounted) {
		this.userDiscounted = userDiscounted;
	}

	public Integer getUserDiscount() {
		return userDiscount;
	}

	public void setUserDiscount(Integer userDiscount) {
		this.userDiscount = userDiscount;
	}

	public User(String email, String password, byte[] profilePic, String firstName, String lastName, boolean isAdmin,
			Boolean userDiscounted, Integer userDiscount) {
		super();
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.userDiscounted = userDiscounted;
		this.userDiscount = userDiscount;
	}

	public User(int userId, String email, String password, byte[] profilePic, String firstName, String lastName,
			boolean isAdmin, Boolean userDiscounted, Integer userDiscount) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.profilePic = profilePic;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
		this.userDiscounted = userDiscounted;
		this.userDiscount = userDiscount;
	}


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
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	public User(String email, String password, String firstName, String lastName) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(profilePic);
		result = prime * result + userDiscount;
		result = prime * result + (userDiscounted ? 1231 : 1237);
		result = prime * result + userId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (!Arrays.equals(profilePic, other.profilePic))
			return false;
		if (userDiscount != other.userDiscount)
			return false;
		if (userDiscounted != other.userDiscounted)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", profilePic="
				+ Arrays.toString(profilePic) + ", firstName=" + firstName + ", lastName=" + lastName + ", isAdmin="
				+ isAdmin + ", userDiscounted=" + userDiscounted + ", userDiscount=" + userDiscount + "]";
	}

	
	
}
