package com.jpa.shipment.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class OrderTest1 {

	public UserTest getUserTest() {
		return userTest;
	}

	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}

	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use appropriate strategy for your DB
	private Long id;

	private String description;
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "userTest_id", nullable = false)
	private UserTest userTest;

	@OneToMany(mappedBy = "orderTest", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Shipment> shipments;

	// Default constructor
	public OrderTest1() {
	}

	// Constructor
	public OrderTest1(Long id, String description, Double amount) {
		this.id = id;
		this.description = description;
		this.amount = amount;
	}

	public OrderTest1(String description, Double amount, UserTest user) {
		this.description = description;
		this.amount = amount;
		this.userTest = user;
	}

	// Getter and Setter methods
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order{id=" + id + ", description='" + description + "', amount=" + amount + "}";
	}
}