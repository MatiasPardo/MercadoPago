package com.mercadopago.model;

import java.util.List;

public class PreferenceResponse {
	
	private String id;
    private List<String> items;
    private String notification_url;
    private String external_reference;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
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
}
