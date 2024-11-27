package com.jpa.shipment.repo;

import java.util.ArrayList;
import java.util.List;

import com.jpa.shipment.entity.UserTest;
import com.jpa.shipment.utils.PersistenceConfig;
import com.jpa.shipment.utils.PropertyLoader;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

	private List<User> users = new ArrayList<>();

	public UserRepositoryImpl() {
	}

	/*
	 * public static UserRepositoryImpl getInstance() { synchronized
	 * (UserRepositoryImpl.class) { if (userRepositoryImpl == null) {
	 * userRepositoryImpl = new UserRepositoryImpl(); } }
	 * 
	 * return userRepositoryImpl;
	 * 
	 * }
	 */

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
	public UserTest addUser(UserTest user) {
		try {
			System.out.println("Saving order: " + user);
			entityManager.getTransaction().begin(); // Start a transaction
			entityManager.persist(user); // Persist the entity
			entityManager.getTransaction().commit(); // Commit the transaction

			System.out.println("Order persisted successfully: " + user);
		} catch (Exception e) {
			System.err.println("Error saving order: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserTest getUser(String userId) {
		return entityManager.find(UserTest.class, Long.getLong(userId));
	}

	@Override
	public List<UserTest> getUsers() {
		return entityManager.createQuery("SELECT o FROM User o", UserTest.class).getResultList();
	}

	@Override
	public void deleteUser(String userId) {
		UserTest user = getUser(userId);
		if (user != null) {
			entityManager.remove(user);
			System.out.println("delete completed");
		}

	}

	@Override
	public UserTest updateUser(String id, UserTest user) {
		entityManager.merge(user);
		return null;
	}



}
