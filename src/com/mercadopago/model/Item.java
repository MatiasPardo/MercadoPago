package com.mercadopago.model;

import java.math.BigDecimal;

public class Item {
	
	private String title;
	private String description;
	private String picture_url;
	private String category_id;
	private int quantity;
	private String currency_id;
	private BigDecimal unit_price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture_url() {
		return picture_url;
	}
	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}
	public BigDecimal getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

}
