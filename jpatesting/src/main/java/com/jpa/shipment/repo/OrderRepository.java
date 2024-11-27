package com.jpa.shipment.repo;

import java.util.List;

import com.jpa.shipment.entity.OrderTest1;


public interface OrderRepository {

	OrderTest1 findById(Long id);

	List<OrderTest1> findAll();

	void save(OrderTest1 order);

	void update(OrderTest1 order);

	void delete(Long id);

}
