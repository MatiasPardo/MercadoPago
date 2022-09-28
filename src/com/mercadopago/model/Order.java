package com.mercadopago.model;

import java.util.LinkedList;

import javax.xml.bind.ValidationException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Order {
	
	private String id;
	private String status;
	private String external_reference;
	private LinkedList<Payment> payments;
	private String order_status;
	
	public static void createOrder(MercadoPago mercadoPago, Box box, Integer amount, 
									String esquema, String idNotificacion) throws Exception {
		JsonObject jsonOrder = new JsonObject();
		jsonOrder.addProperty("external_reference", idNotificacion);
		jsonOrder.addProperty( "title", "Title");
		jsonOrder.addProperty( "description", "Mercado Pago");
		jsonOrder.addProperty( "notification_url", "https://clouderpgo.com:8443/CloudFactory/notificacionesml.jsp?esquema=" + esquema + "&id=" + idNotificacion);
		//jsonOrder.addProperty( "expiration_date", "2023-08-22T16:34:56.559-04:00");
		jsonOrder.addProperty( "total_amount", amount);
		JsonObject jsonItem = new JsonObject();
		jsonItem.addProperty("sku_number", "KS955RUR");
		jsonItem.addProperty("category", "FOOD");
		jsonItem.addProperty("title", "Item1");
		jsonItem.addProperty("description", "Item1 Mercado Pago");
		jsonItem.addProperty("unit_price", amount);
		jsonItem.addProperty("quantity", 1);
		jsonItem.addProperty("unit_measure", "unit");
		jsonItem.addProperty("total_amount", amount);
		JsonArray jsonItems = new JsonArray();
		jsonItems.add(jsonItem);
		jsonOrder.add("items", jsonItems);
//		JsonObject jsonSponsor = new JsonObject();
//		jsonSponsor.addProperty("id", Integer.valueOf(box.getId()));
//		jsonOrder.add("sponsor", jsonSponsor);
	
		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(JSON, jsonOrder.toString());
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/instore/qr/seller/collectors/"+ box.getUser_id()
				  			+ "/stores/"+ box.getExternal_store_id()
				  			+ "/pos/" + box.getExternal_id() 
				  			+ "/orders")
				  .method("PUT", requestBody)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		MercadoPago.sendRequest(request);
	}
	
	public static Order getOrder(MercadoPago mercadoPago, String externalReference) throws Exception {
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/merchant_orders/search?external_reference=" + externalReference)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		String response = MercadoPago.sendRequest(request);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
		Order order = null;
		try{
			if(jsonObject.get("total").getAsInt() > 1)
				throw new ValidationException("Se encontro mas de una orden para el pago");
			
			if(jsonObject.get("total").getAsInt() == 0)
				throw new ValidationException("No se encontro ninguna orden para el pago");
			
			order = new Gson().fromJson(jsonObject.get("elements").getAsJsonArray().get(0), Order.class);
		}catch(Exception e){
			throw new Exception(e);
		}
		
		return order;
	}
	
	public static Order getOrderLink(MercadoPago mercadoPago, String id) throws Exception {
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/merchant_orders/search?preference_id=" + id)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		String response = MercadoPago.sendRequest(request);
		JsonObject jsonObject = new Gson().fromJson(response, JsonObject.class);
	
		if(jsonObject.get("total").getAsInt() > 1)
			throw new ValidationException("Se encontro mas de una orden para el pago");
		
		if(jsonObject.get("total").getAsInt() == 0)
			throw new ValidationException("No se encontro ninguna orden para el pago");
		
		Order order = new Gson().fromJson(jsonObject.get("elements").getAsJsonArray().get(0), Order.class);
		
		return order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LinkedList<Payment> getPayments() {
		return payments;
	}

	public void setPayments(LinkedList<Payment> payments) {
		this.payments = payments;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExternal_reference() {
		return external_reference;
	}

	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

}
