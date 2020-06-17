package com.revature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_TABLE")
public class Account {

    @Column(name = "ACC_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCID_PK_SEQ")
	private Long accId;
	
    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "U_ID_FK", referencedColumnName = "U_ID")
    private Long userId;
	
    @OneToOne(targetEntity = AccountType.class)
    @JoinColumn(name = "ACCTYPE_ID_FK", referencedColumnName = "ACCTYPE_ID")
    private Long accTypeId;
	
    @Column(name = "POINTS", columnDefinition = "INTEGER DEFAULT 0")
	private int points;

	public Account() {
		super();
	}

	public Account(Long userId, Long accTypeId, int points) {
		super();
		this.userId = userId;
		this.accTypeId = accTypeId;
		this.points = points;
	}

	
	
	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(Long accTypeId) {
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
