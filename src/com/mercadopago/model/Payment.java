package com.mercadopago.model;

import java.math.BigDecimal;

import com.google.gson.Gson;

import okhttp3.Request;

public class Payment {
	
	private String id;
	private String collector_id;
	private String date_approved;
	private Order order;
	private String pos_id;
	private String store_id; 
	private String status;
	private BigDecimal transaction_amount;
	
	public static Payment getPayment(MercadoPago mercadoPago, String paymentId) throws Exception {
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/v1/payments/" + paymentId)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		String response = MercadoPago.sendRequest(request);
		
		Payment paymentResponse = new Gson().fromJson(response, Payment.class);
		return paymentResponse;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollector_id() {
		return collector_id;
	}
	public void setCollector_id(String collector_id) {
		this.collector_id = collector_id;
	}
	public String getDate_approved() {
		return date_approved;
	}
	public void setDate_approved(String date_approved) {
		this.date_approved = date_approved;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getPos_id() {
		return pos_id;
	}
	public void setPos_id(String pos_id) {
		this.pos_id = pos_id;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(BigDecimal transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	
}
