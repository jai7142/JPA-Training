package com.jpa.shipment.repo;

import java.util.List;

import com.jpa.shipment.entity.UserTest;

public interface UserRepository {

	public UserTest addUser(UserTest user);

	public UserTest getUser(String userId);

	public List<UserTest> getUsers();

	public void deleteUser(String userId);

	public UserTest updateUser(String id, UserTest user);

}
