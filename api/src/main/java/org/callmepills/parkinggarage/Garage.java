package org.callmepills.parkinggarage;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Garage {

	@Id
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
