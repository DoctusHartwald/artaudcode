package com.myshop.domain.plate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;
    private String lastName;
    private String firstName;

    public Person(String lastname,String firstName) {
		this.lastName=lastname;
		this.firstName=firstName;
	}
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", id=" + id + ", lastName="
				+ lastName + "]";
	}
}
