package com.jpa.shipment.main;

import java.util.Scanner;

import com.jpa.shipment.entity.OrderTest1;
import com.jpa.shipment.entity.Shipment;
import com.jpa.shipment.entity.UserTest;
import com.jpa.shipment.repo.OrderRepositoryImpl;
import com.jpa.shipment.repo.ShipmentRepositoryImpl;
import com.jpa.shipment.repo.UserRepositoryImpl;

class MainConsoleTest {
	private final static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("operation are 1.user,2.oder,3.ship");
		System.out.println("Please enter the input");
		String choice = scanner.next();

		String str = choice;
		switch (str) {
		case "user":
			userCRUD();
			break;
		case "order":
			orderCRUD();
			break;
		case "ship":
			shipmentCRUD();
			break;
		default:
			System.out.println("does not match the cases");
		}
	}

	public static void orderCRUD() {
		OrderTest1 newOrder = new OrderTest1(21l, "Sample Order", 100.0);

		OrderRepositoryImpl orderRepositoryImpl = new OrderRepositoryImpl();
		orderRepositoryImpl.init();
		orderRepositoryImpl.save(newOrder);
		System.out.println(newOrder.getId());
		OrderTest1 newOrder2 = new OrderTest1(newOrder.getId(), "Sample testes", 435.0);
		orderRepositoryImpl.update(newOrder2);

		// orderRepositoryImpl.delete(21l);

	}

	public static void shipmentCRUD() {
		// Shipment shipment = new Shipment(23L, null, "", "");
		Shipment shipment = null, shipment1 = null;
		ShipmentRepositoryImpl shipmentRepositoryImpl = new ShipmentRepositoryImpl();
		shipmentRepositoryImpl.init();
		shipmentRepositoryImpl.addProduct(shipment);
		System.out.println(shipment.getId());
		// Shipment shipment1 = new Shipment(23L, null, "", "");
		shipmentRepositoryImpl.updateShipment("23L", shipment1);

		// orderRepositoryImpl.delete(21l);

	}

	public static void userCRUD() {
		UserTest userTest = new UserTest(45L, "Jai", "test@123", "admin");

		UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
		userRepositoryImpl.init();
		userRepositoryImpl.addUser(userTest);
		System.out.println(userTest.getId());
		UserTest userTest1 = new UserTest(45L, "Jasi", "tessdst@123", "adsdsmin");
		userRepositoryImpl.updateUser("45L", userTest1);

		// orderRepositoryImpl.delete(21l);

	}
	
}
