package com.mercadopago.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MercadoPago {
	private String token;
	private String userId;
	
	public MercadoPago(String token, String userId) {
		this.token = token;
		this.userId = userId;
	}
	
	public static String sendRequest(Request request ) throws Exception {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Response response = null;
		String responseString = "";
		String error = "";
		String descripcion = "";
		
		response = client.newCall(request).execute();
		responseString = response.body().string();
		if(!response.isSuccessful()) {
			try{
				JsonObject jsonError = new Gson().fromJson(responseString, JsonObject.class);
				error = jsonError.get("message").getAsString();
				descripcion = jsonError.getAsJsonArray("causes").toString();
				if(descripcion.isEmpty())
					throw new Exception("Error API Mercado Pago: " + error);
				else
					throw new Exception("Error API Mercado Pago: " + error + " - Descripcion: " + descripcion);

			}catch(Exception e){
			}
		}
		if(response != null)
			response.close();
		
		
		return responseString;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
