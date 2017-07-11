package com.college.dto;

public class Equipment
{
	private Integer id = 0;

	private Integer belong_type = 0;
	
	private Integer owner = 0;
	private String ownerorg;
	
	private Integer address = 0;
	
	private String name;
	
	private String brand;
	
	private String model;
	
	private String shelf;
	
	private String purchase;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBelong_type() {
		return belong_type;
	}

	public void setBelong_type(Integer belong_type) {
		this.belong_type = belong_type;
	}

	public Integer getOwner() {
		return owner;
	}

	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public String getOwnerorg() {
		return ownerorg;
	}

	public void setOwnerorg(String ownerorg) {
		this.ownerorg = ownerorg;
	}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getPurchase() {
		return purchase;
	}

	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}
	
}
