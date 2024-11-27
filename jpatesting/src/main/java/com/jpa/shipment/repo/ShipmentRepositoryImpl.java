package com.jpa.shipment.repo;

import java.util.ArrayList;
import java.util.List;

import com.jpa.shipment.entity.Shipment;
import com.jpa.shipment.utils.PersistenceConfig;
import com.jpa.shipment.utils.PropertyLoader;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named
@ApplicationScoped
public class ShipmentRepositoryImpl implements ShipmentRepository {

	private static ShipmentRepository shipmentRepository;

	private List<Shipment> shipments = new ArrayList<>();

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

	public ShipmentRepositoryImpl() {
	}

	/*
	 * public static ShipmentRepositoryImpl getInstance() { if
	 * (shipmentRepositoryImpl == null) { shipmentRepositoryImpl = new
	 * ShipmentRepositoryImpl(); } return shipmentRepositoryImpl;
	 * 
	 * }
	 */

	@Override
	public Shipment addProduct(Shipment shipment) {

		try {
			System.out.println("Saving order: " + shipment);
			entityManager.getTransaction().begin(); // Start a transaction
			entityManager.persist(shipment); // Persist the entity
			entityManager.getTransaction().commit(); // Commit the transaction

			System.out.println("Order persisted successfully: " + shipment);
		} catch (Exception e) {
			System.err.println("Error saving order: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Shipment getShipment(String shipmentId) {
		return entityManager.find(Shipment.class, Long.getLong(shipmentId));
	}

	@Override
	public List<Shipment> getShipments() {
		return entityManager.createQuery("SELECT o FROM Shipment o", Shipment.class).getResultList();
	}

	@Override
	public void deleteShipment(String shipmentId) {
		Shipment ship = getShipment(shipmentId);
		if (ship != null) {
			entityManager.remove(ship);
			System.out.println("delete completed");
		}

	}

	@Override
	public Shipment updateShipment(String id, Shipment shipment) {
		entityManager.merge(shipment);
		return null;
	}


}
