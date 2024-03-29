package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {
	@Id
	private int cid;
	private String password;
	private String old_password;
	private int acc_no;
	private int ifsc;
	private String acc_type;
	private int mobile_no;
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Accounts() {
		
	}
	public Accounts(int cid, String password, String old_password, int acc_no, int ifsc, String acc_type,
			int mobile_no,int amount) {
		
		this.cid = cid;
		this.password = password;
		this.old_password = old_password;
		this.acc_no = acc_no;
		this.ifsc = ifsc;
		this.acc_type = acc_type;
		this.mobile_no = mobile_no;
		this.amount= amount;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public int getAcc_no() {
		return acc_no;
	}
	public void setAcc_no(int acc_no) {
		this.acc_no = acc_no;
	}
	public int getIfsc() {
		return ifsc;
	}
	public void setIfsc(int ifsc) {
		this.ifsc = ifsc;
	}
	public String getAcc_type() {
		return acc_type;
	}
	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}
	public int getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(int mobile_no) {
		this.mobile_no = mobile_no;
	}
	
	
}
