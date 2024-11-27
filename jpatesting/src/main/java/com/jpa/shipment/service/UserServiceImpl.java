package com.jpa.shipment.service;

import java.util.List;
import java.util.Optional;

import com.jpa.shipment.entity.User;
import com.jpa.shipment.repo.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository userRepository;

	public UserServiceImpl() {
	}

	/*
	 * public static UserServiceImpl getInstance() { if (userServiceImpl == null) {
	 * userServiceImpl = new UserServiceImpl(); } return userServiceImpl;
	 * 
	 * }
	 */

	@Override
	public User addUser(User user) {
		return this.userRepository.addUser(user);
	}

	@Override
	public Optional<User> getUser(String userId) {

		this.userRepository.getUser(userId);
		return Optional.empty();
	}

	@Override
	public Optional<List<User>> getUsers() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public User updateUser(String id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
