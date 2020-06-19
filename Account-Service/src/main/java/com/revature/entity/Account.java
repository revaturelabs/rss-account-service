package com.revature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TABLE")
public class Account {

    @Column(name = "ACC_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCID_PK_SEQ")
	private int accId;
	
    //@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "U_ID_FK", referencedColumnName = "U_ID")
    @Column(name = "U_ID")
    private int userId;
	
    //@OneToOne(targetEntity = AccountType.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "ACCTYPE_ID_FK", referencedColumnName = "ACCTYPE_ID")
    @Column(name = "ACCTYPE_ID")
    private int accTypeId;
	
    @Column(name = "POINTS", columnDefinition = "INTEGER DEFAULT 0")
	private int points;

	public Account() {
		super();
	}

	
	
	public Account(int accId, int userId, int accTypeId, int points) {
		super();
		this.accId = accId;
		this.userId = userId;
		this.accTypeId = accTypeId;
		this.points = points;
	}

	
	
	public Account(int userId, int accTypeId, int points) {
		super();
		this.userId = userId;
		this.accTypeId = accTypeId;
		this.points = points;
	}

	

	public Account(int userId, int accTypeId) {
		super();
		this.userId = userId;
		this.accTypeId = accTypeId;
	}



	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(int accTypeId) {
		this.accTypeId = accTypeId;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Account [accId=" + accId + ", userId=" + userId + ", accTypeId=" + accTypeId + ", points=" + points
				+ "]";
	}
    
    
	
}
