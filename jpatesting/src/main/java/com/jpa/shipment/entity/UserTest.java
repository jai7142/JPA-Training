package com.jpa.shipment.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class UserTest {

	public List<OrderTest1> getOrdertests() {
		return ordertests;
	}

	public void setOrdertests(List<OrderTest1> ordertests) {
		this.ordertests = ordertests;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use appropriate strategy for your DB
	private Long id;

	private String userName;

	private String password;

	private String role;

	@OneToMany(mappedBy = "userTest", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderTest1> ordertests;

	@Override
	public String toString() {

		return "Id ->" + this.id + "->" + this.userName + "Role->" + this.role;
	}

	// Default constructor
	public UserTest() {
			}

			public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserTest(Long id, String userName, String password, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public UserTest(String string, String string2, String string3) {
		this.userName = string;
		this.password = string2;
		this.role = string3;
	}

}
