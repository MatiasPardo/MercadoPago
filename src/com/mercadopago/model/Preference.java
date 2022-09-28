package com.mercadopago.model;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;



public class Preference {

	private String id;
    private List<Item> items;
    private String notification_url;
    private String external_reference;
    
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public PaymentLink requestLink(MercadoPago mercadoPago) throws Exception {

		MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		RequestBody requestBody = RequestBody.create(JSON, new Gson().toJson(this));
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/checkout/preferences")
				  .method("POST", requestBody)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		String response = MercadoPago.sendRequest(request);
		PaymentLink link = null;
		try{
			link = new Gson().fromJson(response, PaymentLink.class);
		}catch(Exception e){
			throw new Exception(response);
		}
		
		return link;
		
	}
	
	public static PaymentLink getPaymentLink(MercadoPago mercadoPago, String id) throws Exception {

		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/checkout/preferences/"+id)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		String response = MercadoPago.sendRequest(request);
		PaymentLink link = null;
		try{
			link = new Gson().fromJson(response, PaymentLink.class);
		}catch(Exception e){}
		
		return link;
		
	}
	
	public static List<PreferenceResponse> searchPaymentLink(MercadoPago mercadoPago, String external_reference) throws Exception {

		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/checkout/preferences/search?external_reference="+external_reference)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		JsonObject response = new Gson().fromJson(MercadoPago.sendRequest(request),JsonObject.class);
		List<PreferenceResponse> links = new LinkedList<PreferenceResponse>();
		try{
			for(JsonElement elem: response.get("elements").getAsJsonArray()){
				PreferenceResponse payLink = new Gson().fromJson(elem.toString(), PreferenceResponse.class);
				links.add(payLink);
			} 
		}catch(Exception e){
			throw new Exception(response != null ? response.toString():e.getMessage());
		}
		
		return links;
		
	}

	public String getNotification_url() {
		return notification_url;
	}

	public void setNotification_url(String notification_url) {
		this.notification_url = notification_url;
	}

	public String getExternal_reference() {
		return external_reference;
	}

	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
}