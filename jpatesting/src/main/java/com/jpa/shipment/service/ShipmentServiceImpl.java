package com.jpa.shipment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jpa.shipment.entity.Shipment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class ShipmentServiceImpl implements ShipmentService {

	private static ShipmentService shipmentService;

	private List<Shipment> shipments = new ArrayList<>();

	public ShipmentServiceImpl() {
	}

	/*
	 * public static ShipmentServiceImpl getInstance() { if (shipmentServiceImpl ==
	 * null) { shipmentServiceImpl = new ShipmentServiceImpl(); } return
	 * shipmentServiceImpl;
	 * 
	 * }
	 */
	@Override
	public Shipment addProduct(Shipment shipment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Shipment> getShipment(String shipmentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<List<Shipment>> getShipments() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteShipment(String shipmentId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Shipment updateShipment(String id, Shipment shipment) {
		// TODO Auto-generated method stub
		return null;
	}

}
