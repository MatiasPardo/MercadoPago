package com.mercadopago.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PaymentLink {

	private String id;
	private String additional_info;
	private String client_id;
	private int collector_id;
//	private Date date_created; 
//	private Date date_of_expiration; 
	private String init_point;
	private String notification_url;
	private String sandbox_init_point;
	private String site_id;
	private List<Item> items;
	private BigDecimal total_amount;
	private String external_reference;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public int getCollector_id() {
		return collector_id;
	}
	public void setCollector_id(int collector_id) {
		this.collector_id = collector_id;
	}
//	public Date getDate_created() {
//		return date_created;
//	}
//	public void setDate_created(Date date_created) {
//		this.date_created = date_created;
//	}
//	public Date getDate_of_expiration() {
//		return date_of_expiration;
//	}
//	public void setDate_of_expiration(Date date_of_expiration) {
//		this.date_of_expiration = date_of_expiration;
//	}
	public String getInit_point() {
		return init_point;
	}
	public void setInit_point(String init_point) {
		this.init_point = init_point;
	}
	public String getNotification_url() {
		return notification_url;
	}
	public void setNotification_url(String notification_url) {
		this.notification_url = notification_url;
	}
	public String getSandbox_init_point() {
		return sandbox_init_point;
	}
	public void setSandbox_init_point(String sandbox_init_point) {
		this.sandbox_init_point = sandbox_init_point;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public String getExternal_reference() {
		return external_reference;
	}
	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}
	
	
}
