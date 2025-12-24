package entities;

import interfaces.IMapp;

public class Entity implements IMapp{
	
private int id;
	
	public Entity() {
		
	}
	
	public Entity(int id) {
		setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "ID : " + id + "\n";
	}

}	
