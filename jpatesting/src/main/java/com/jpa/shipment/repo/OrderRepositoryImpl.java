package com.jpa.shipment.repo;

import java.util.List;

import com.jpa.shipment.entity.OrderTest1;
import com.jpa.shipment.utils.PersistenceConfig;
import com.jpa.shipment.utils.PropertyLoader;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

	// @Inject
	private PersistenceConfig persistenceConfig;

	// @PersistenceContext
	private EntityManager entityManager;
	// @Inject
	private PropertyLoader propertyLoader;

	// @PostConstruct
	public void init() {
		propertyLoader = new PropertyLoader(); // Assume you have this implemented
		persistenceConfig = new PersistenceConfig(); // Assume this handles persistence configurations

		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("shippingPU",
					propertyLoader.loadProperties("application.properties"));
			entityManager = emf.createEntityManager();
			System.out.println("EntityManager initialized successfully: " + entityManager);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(
					"Failed to initialize EntityManager. Check persistence.xml and dependencies.", e);
		}



	}


	@Override
	public OrderTest1 findById(Long id) {
		return entityManager.find(OrderTest1.class, id);
	}

	@Override
	public List<OrderTest1> findAll() {
		return entityManager.createQuery("SELECT o FROM Orders o", OrderTest1.class).getResultList();
	}

	@Override
	public void save(OrderTest1 order) {
		try {
			System.out.println("Saving order: " + order);
			entityManager.getTransaction().begin(); // Start a transaction
			entityManager.persist(order); // Persist the entity
			entityManager.getTransaction().commit(); // Commit the transaction
			entityManager.close();
			System.out.println("Order persisted successfully: " + order);
		} catch (Exception e) {
			System.err.println("Error saving order: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void update(OrderTest1 order) {
		init();
		System.out.println("update before" + order);
		entityManager.persist(order);
		System.out.println("update after" + order);
	}

	@Override
	public void delete(Long id) {
		OrderTest1 order = findById(id);
		if (order != null) {
			entityManager.remove(order);
			System.out.println("delete completed");
		}
	}

}

