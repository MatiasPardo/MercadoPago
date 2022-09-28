package com.mercadopago.model;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Store {
	private String id;
	private String name;
	private String external_id;
	
	public static Store createStore(MercadoPago mp, String name, String external_id, Location location) throws Exception {
		MediaType mediaType = MediaType.parse("application/json");
		JsonObject jsonStore = new JsonObject();
		jsonStore.addProperty("name", name);
		jsonStore.addProperty("external_id", external_id);
		JsonObject jsonLocation = new JsonObject();
		jsonLocation.addProperty("street_number", location.getStreet_number());
		jsonLocation.addProperty("street_name", location.getStreet_name());
		jsonLocation.addProperty("city_name", location.getCity_name());
		jsonLocation.addProperty("state_name", location.getState_name());
		jsonLocation.addProperty("latitude", location.getLatitude());
		jsonLocation.addProperty("longitude", location.getLongitude());
		jsonStore.add("location", jsonLocation);
		
		RequestBody body = RequestBody.create(mediaType, jsonStore.toString());
		Request request = new Request.Builder()
		  .url("https://api.mercadopago.com/users/" + mp.getUserId() + "/stores")
		  .method("POST", body)
		  .addHeader("Authorization", "Bearer " + mp.getToken())
		  .addHeader("Content-Type", "application/json")
		  .build();

		String responseString = MercadoPago.sendRequest(request);
		return new Gson().fromJson(responseString, Store.class);
	}
	
	public static List<Store> getStores(MercadoPago mercadoPago) throws Exception{
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/users/" + mercadoPago.getUserId() + "/stores/search")
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		String storesResponse = MercadoPago.sendRequest(request);
		List<Store> stores = new LinkedList<Store>();
		JsonObject jsonStores = new Gson().fromJson(storesResponse, JsonObject.class);
		JsonArray arrayStores = jsonStores.get("results").getAsJsonArray();
		for(JsonElement element : arrayStores){
			Store store = new Gson().fromJson(element, Store.class);
			stores.add(store);
		}

		return stores;
	}
	
	public static Store getStore(MercadoPago mercadoPago, String idStore) throws Exception {
		Request request = new Request.Builder()
		  .url("https://api.mercadopago.com/stores/" + idStore)
		  .method("GET", null)
		  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
		  .build();
		String response = MercadoPago.sendRequest(request);
		Store storeResponse = new Gson().fromJson(response, Store.class);
		
		return storeResponse;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	
}
