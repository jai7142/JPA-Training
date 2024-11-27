package com.jpa.shipment.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jpa.shipment.entity.OrderTest1;
import com.jpa.shipment.entity.Shipment;
import com.jpa.shipment.entity.UserTest;
import com.jpa.shipment.utils.PropertyLoader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ShipmentMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {

			PropertyLoader propertyLoader = new PropertyLoader();
			// Create an EntityManagerFactory and EntityManager
			emf = Persistence.createEntityManagerFactory("shippingPU",
					propertyLoader.loadProperties("application.properties"));

			em = emf.createEntityManager();

			// addData(em, emf);

			// How can you retrieve all orders placed by a specific user?
			/*
			 * UserTest testdata = em.find(UserTest.class, 34l);
			 * 
			 * testdata.getOrdertests().stream() .forEach(e ->
			 * System.out.println("Username ->" + testdata.getUserName() + ",,Order Des ->"
			 * + e.getDescription() + ",,Order Amount->" + e.getAmount()));
			 */

			getOrderWithUserName("ravi", em);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (emf != null && em != null) {
				em.close();
				emf.close();

			}
		}

	}

	public static void addData(EntityManager em, EntityManagerFactory emf) {
		em.getTransaction().begin();

		List<UserTest> users;
		List<OrderTest1> orders;
		List<Shipment> shiments;

		UserTest uT1 = new UserTest("jai", "test@123", "admin");
		UserTest uT2 = new UserTest("kumar", "test@123", "supervisor");
		UserTest uT3 = new UserTest("ravi", "test@123", "labour");

		OrderTest1 oT1 = new OrderTest1("Wood things", 400.4d, uT1);
		OrderTest1 oT2 = new OrderTest1("Plastic", 454.4d, uT1);
		OrderTest1 oT3 = new OrderTest1("Stone", 656.4d, uT2);
		OrderTest1 oT4 = new OrderTest1("accessories", 234.67d, uT2);
		OrderTest1 oT5 = new OrderTest1("cloths", 284.4d, uT3);
		OrderTest1 oT6 = new OrderTest1("drinks", 2356.4d, uT3);

		Shipment ship = new Shipment("SH-001", "Kumar-001", oT1);
		Shipment ship1 = new Shipment("SH-001", "Kumar-001", oT1);
		Shipment ship2 = new Shipment("SH-001", "Kumar-001", oT2);
		Shipment ship3 = new Shipment("SH-001", "Kumar-001", oT2);
		Shipment ship4 = new Shipment("SH-001", "Kumar-001", oT3);
		Shipment ship5 = new Shipment("SH-001", "Kumar-001", oT3);
		Shipment ship6 = new Shipment("SH-001", "Kumar-001", oT4);
		Shipment ship7 = new Shipment("SH-001", "Kumar-001", oT4);
		Shipment ship8 = new Shipment("SH-001", "Kumar-001", oT5);
		Shipment ship9 = new Shipment("SH-001", "Kumar-001", oT5);
		Shipment ship10 = new Shipment("SH-001", "Kumar-001", oT6);
		Shipment ship11 = new Shipment("SH-001", "Kumar-001", oT6);

		shiments = Arrays.asList(ship, ship1, ship2, ship3, ship4, ship5, ship6, ship7, ship8, ship9, ship10, ship11);

		orders = Arrays.asList(oT1, oT2, oT3, oT4, oT5, oT6);

		users = Arrays.asList(uT1, uT2, uT3);

		for (UserTest u : users) {
			em.persist(u);
			for (OrderTest1 o : orders) {
				if (o.getUserTest().equals(u)) {
					em.persist(o);
					for (Shipment s : shiments) {
						if (s.getOrderTest().equals(o)) {
							em.persist(s);
						}
					}
				}
			}
		}

		em.getTransaction().commit();

		System.out.println("Successfully created all the Records.");
	}

	// How do you query for orders based on a user's username?
	public static Optional<List<UserTest>> getOrderWithUserName(String userName, EntityManager em) {
		List<UserTest> orders = em.createQuery("SELECT e FROM UserTest e WHERE e.userName = :userName", UserTest.class)
				.setParameter("userName", userName).getResultList();
		if (!orders.isEmpty()) {
			orders.stream().forEach(e -> {
				System.out.println(e);
				e.getOrdertests().stream().forEach(o -> System.out.println(o.getDescription()));
			});
			return Optional.of(orders);

		} else {
			return Optional.empty();
		}
	}

	// How do you fetch all users with their roles and orders?
	public static Optional<List<UserTest>> getUsers(EntityManager em) {
		List<UserTest> users = em.createQuery("SELECT e FROM UserTest e", UserTest.class).getResultList();
		if (!users.isEmpty()) {
			users.stream().forEach(e -> {
				System.out.println(e);
				e.getOrdertests().stream().forEach(o -> System.out.println("Order ID->" + o.getId() + ",,Order Des->"
						+ o.getDescription() + ",,Order Amount->" + o.getAmount()));
			});
			return Optional.of(users);

		} else {
			return Optional.empty();
		}
	}
}
