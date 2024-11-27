package com.jpa.shipment.entity;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@Override
	public int hashCode() {
		return Objects.hash(id, name, employees);
	}

	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", name='" + name + '\'' + ", employees=" + employees + '}';
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Department) {
			Department that = (Department) object;
			return Objects.equals(this.id, that.id) && Objects.equals(this.name, that.name)
					&& Objects.equals(this.employees, that.employees);
		}
		return false;
	}

	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Employee> employees;

	// Orphan Removal:
	// Both List and Set work with orphanRemoval.
//    However, with a Set, care must be taken to update 
//    the collection properly, as removing elements 
//    depends on correctly implemented 
//    equals and hashCode.
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}