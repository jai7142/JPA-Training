package com.jpa.shipment.utils;

import java.util.Properties;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

//@ApplicationScoped
public class PersistenceConfig {

	// @Inject
	private PropertyLoader propertyLoader; // Inject PropertyLoader to load the properties dynamically

	private EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		System.out.println("Initializing PersistenceConfig...");

		// Load properties from the application.properties file
		Properties dbProperties = propertyLoader.loadProperties("application.properties");

		// Create EntityManagerFactory with properties loaded dynamically
		this.entityManagerFactory = Persistence.createEntityManagerFactory("shippingPU", dbProperties);

		if (entityManagerFactory == null) {
			throw new IllegalStateException("EntityManagerFactory could not be created.");
		}

		System.out.println("EntityManagerFactory initialized successfully.");
	}

	@Produces
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static void main(String[] args) {

		// try (SeContainer container =
		// SeContainerInitializer.newInstance().initialize()) {
		// PropertyLoader loader = container.select(PropertyLoader.class).get();
		PropertyLoader loader = new PropertyLoader();
			System.out.println(loader != null);
			System.out.println(loader.loadProperties("application.properties"));

			// PersistenceConfig config = container.select(PersistenceConfig.class).get();
			// System.out.println(config != null);

			// System.out.println(config.getEntityManagerFactory());
			// }
	}
}
