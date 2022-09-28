package com.mercadopago.model;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class Box {
	private String id;
	private QRBox qr;
	private String status;
	private String date_created;
	private String date_last_updated;
	private String uuid;
	private String user_id;
	private String name;
	private Boolean fixed_amount;
	private String store_id;
	private String external_store_id;
	private String external_id;
	private String site;
	private String qr_code;
	private String category;
	
	public static Box createBox(MercadoPago mercadoPago, String name, String external_id, String storeId) throws Exception {		
		Store store = Store.getStore(mercadoPago, storeId);
		
		MediaType mediaType = MediaType.parse("application/json");
		JsonObject jsonBox = new JsonObject();
		jsonBox.addProperty("name", name);
		jsonBox.addProperty("fixed_amount", true);
		jsonBox.addProperty("external_store_id", store.getExternal_id());
		jsonBox.addProperty("external_id", external_id);
		
		RequestBody body = RequestBody.create(mediaType, jsonBox.toString());
		Request request = new Request.Builder()
		  .url("https://api.mercadopago.com/pos")
		  .method("POST", body)
		  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
		  .addHeader("Content-Type", "application/json")
		  .build();

		String responseString = MercadoPago.sendRequest(request);
		return new Gson().fromJson(responseString, Box.class);
	}
	
	public static List<Box> getBoxes(MercadoPago mercadoPago) throws Exception{		
		Request request = new Request.Builder()
		  .url("https://api.mercadopago.com/pos?access_token=" + mercadoPago.getToken())
		  .method("GET", null)
		  .build();

		String boxesResponse = MercadoPago.sendRequest(request);
		List<Box> boxes = new LinkedList<Box>();	

		JsonObject jsonBoxes = new Gson().fromJson(boxesResponse, JsonObject.class);
		String boxesString = jsonBoxes.get("results").getAsJsonArray().toString();
		List<Box> boxesList = new Gson().fromJson(boxesString, new TypeToken<List<Box>>(){}.getType());
		for(Box box : boxesList)
			boxes.add(box);
		
		return boxes;
	}
	
	public static Box getBox(MercadoPago mercadoPago, String idBox) throws Exception {
		Request request = new Request.Builder()
				  .url("https://api.mercadopago.com/pos/" + idBox)
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer " + mercadoPago.getToken())
				  .build();
		
		String stringResponse = MercadoPago.sendRequest(request);
		return new Gson().fromJson(stringResponse, Box.class);
	}
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public QRBox getQr() {
		return qr;
	}
	public void setQr(QRBox qr) {
		this.qr = qr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_last_updated() {
		return date_last_updated;
	}
	public void setDate_last_updated(String date_last_updated) {
		this.date_last_updated = date_last_updated;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getFixed_amount() {
		return fixed_amount;
	}
	public void setFixed_amount(Boolean fixed_amount) {
		this.fixed_amount = fixed_amount;
	}
	public String getStore_id() {
		return store_id;
	}
	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}
	public String getExternal_store_id() {
		return external_store_id;
	}
	public void setExternal_store_id(String external_store_id) {
		this.external_store_id = external_store_id;
	}
	public String getExternal_id() {
		return external_id;
	}
	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getQr_code() {
		return qr_code;
	}
	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
