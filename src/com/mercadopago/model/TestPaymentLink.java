package com.mercadopago.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;


public class TestPaymentLink {
	
	public static void main(String[] args) throws Exception {	
//		String token = "TEST-1699957472917890-022822-901471310d738e87300b62b78f9ed0e2__LC_LD__-18688378"; 
//		String userId = "18688378";
		String token = "APP_USR-8804641766649030-071213-e6104659fa12589921d66e659a146077-1159323821"; 
		String userId = "1159323821";
		
		MercadoPago mercadoPago = new MercadoPago(token, userId);
//		mercadoPago.setToken(token);
//		mercadoPago.setUserId(userId);
		
		Preference pref = new Preference();
		Item it = new Item();
		it.setCategory_id("car_electronics");
		it.setQuantity(1);
		it.setCurrency_id("$");
		it.setUnit_price(new BigDecimal(11.5));
		
		List<Item> items = new LinkedList<Item>();
		items.add(it);
		
		pref.setItems(items);
		pref.setNotification_url("http://e6d2-181-164-138-50.ngrok.io/CloudFactory/o/MoraPanty/notificacionesml.jsp");
		pref.setExternal_reference("11Empresa A"); // preference_id = 18688378-2e2f99a8-6bda-4d25-aed2-e5e7cbfc5848 / 18688378-224dce78-9d51-497b-a3a5-d7b358876ebd
		
//		System.out.println("============CREACION==========");
//		PaymentLink responseLink = pref.requestLink(mercadoPago);
//		System.out.println("LINK: " + responseLink.getInit_point());
//		System.out.println("ID: " + responseLink.getId());
//		System.out.println("FECHA: " +responseLink.getDate_created());
//		System.out.println("EXT REF: " +responseLink.getExternal_reference());
		
		System.out.println("============Consulta orden==========");
		Order or = Order.getOrder(mercadoPago, pref.getExternal_reference());
		System.out.println("order estatus: " + or.getOrder_status());

		
		System.out.println("============CONSULTA==========");
//		System.out.println(Preference.getPaymentLink(mercadoPago, "18688378-2e2f99a8-6bda-4d25-aed2-e5e7cbfc5848"));
//		List<PreferenceResponse> paymentLinks = Preference.searchPaymentLink(mercadoPago, pref.getExternal_reference());
//		for(PreferenceResponse paymentLink: paymentLinks){
//			System.out.println("ID Preference: " + paymentLink.getId());
//			System.out.println("ITEM: " +paymentLink.getItems().get(0));
//			System.out.println("url: " +paymentLink.getNotification_url());
//			System.out.println("Json: " + new Gson().toJson(paymentLink));	
//		}

			
		
//un id: 1159323821-7cf4135a-b68e-473d-8932-1ab8dc1bb3f7
//otro : 1159323821-e62a50b2-bc30-4de0-a0f4-2eaf9c182e41		
// otro: 1159323821-21f14bb8-423e-458b-8f84-a4dd74631ef9	
	}


}
