package com.revature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCTYPE_TABLE")
public class AccountType {

    @Column(name = "ACCTYPE_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCTYPEID_PK_SEQ")
	private int accTypeId;
    
    @Column(name = "TYPE", unique = true, nullable = false)
    private String type;

	public AccountType() {
		super();
	}

	public AccountType(String type) {
		super();
		this.type = type;
	}

	public int getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(int accTypeId) {
		this.accTypeId = accTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AccountType [accTypeId=" + accTypeId + ", type=" + type + "]";
	}
	
    
    
}
