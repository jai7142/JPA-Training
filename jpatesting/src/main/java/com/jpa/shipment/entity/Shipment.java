package com.jpa.shipment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Shipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Use appropriate strategy for your DB
	private Long id;

	private String trackingNumber;

	private String assignedDriver;

	@ManyToOne
	@JoinColumn(name = "orderTest_id", nullable = false)
	private OrderTest1 orderTest;

	// Default constructor
	public Shipment() {
		}

		public Shipment(Long id, String trackingNumber, String assignedDriver) {
		super();
		this.id = id;
		this.trackingNumber = trackingNumber;
		this.assignedDriver = assignedDriver;
	}

	public Shipment(String trackingNumber, String assignedDriver, OrderTest1 ot) {
		this.trackingNumber = trackingNumber;
		this.assignedDriver = assignedDriver;
		this.orderTest = ot;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public OrderTest1 getOrderTest() {
		return orderTest;
	}

	public void setOrderTest(OrderTest1 orderTest) {
		this.orderTest = orderTest;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getAssignedDriver() {
		return assignedDriver;
	}

	public void setAssignedDriver(String assignedDriver) {
		this.assignedDriver = assignedDriver;
	}
}
