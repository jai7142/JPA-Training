package com.jpa.shipment.repo;

import java.util.List;

import com.jpa.shipment.entity.Shipment;

public interface ShipmentRepository {

	public Shipment addProduct(Shipment shipment);

	public Shipment getShipment(String shipmentId);

	public List<Shipment> getShipments();

	public void deleteShipment(String shipmentId);

	public Shipment updateShipment(String id, Shipment shipment);
}
