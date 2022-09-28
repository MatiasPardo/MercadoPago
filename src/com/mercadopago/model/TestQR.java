package com.mercadopago.model;

import java.io.IOException;
import java.util.List;

public class TestQR {
	
	public static void main(String[] args) throws IOException {	
		String token = "APP_USR-8804641766649030-071213-e6104659fa12589921d66e659a146077-1159323821"; 
		String userId = "1159323821";
		
		MercadoPago mercadoPago = new MercadoPago(token, userId);
		mercadoPago.setToken(token);
		mercadoPago.setUserId(userId);

		try {
			//Creo sucursal
			Location location = new Location();
			location.setStreet_number("3039");
			location.setStreet_name("Juramento");
			location.setCity_name("Belgrano");
			location.setState_name("Capital Federal");
			location.setLatitude(-32.8897322);
			location.setLongitude(-32.8897322);
			Store.createStore(mercadoPago, "SucursalJava", "SJ", location);
	
			//Muestro sucursales
			List<Store> stores = Store.getStores(mercadoPago);
			for(Store store : stores){
				System.out.println("Sucursal: " + store.getExternal_id());    
	        }
			
			//Selecciono sucursal
			String storeId = "48181742";
			Store storeResponse = Store.getStore(mercadoPago, storeId);
			System.out.println("Sucursal seleccionada: " + storeResponse.getExternal_id());
			
			System.out.println("\n--------------------------------------\n");
			
			//Creo caja
			Box.createBox(mercadoPago, "CajaJava", "CJ", storeId);
			
			//Muestro cajas		
			List<Box> boxes = Box.getBoxes(mercadoPago);
			for(Box box : boxes){
				System.out.println("Caja: " + box.getExternal_id());    
	        }
			
			//Selecciono caja
			String boxId = "49681996";  
	 		Box boxResponse = Box.getBox(mercadoPago, boxId);
			System.out.println("Caja seleccionada: " + boxResponse.getExternal_id());
			System.out.println("QR: " + boxResponse.getQr().getImage());
			
			System.out.println("\n--------------------------------------\n");
			
			//Creo orden de pago
			Order.createOrder(mercadoPago, boxResponse, 50, "MoraPanty", "8a1e80836bdba790016be6a23a74");
			System.out.println("Orden de pago creada");
			
			//Busco orden de pago
			Order order = Order.getOrder(mercadoPago, "8a1e80836bdba790016be6a23a74");
			System.out.println("Orden: " + order.getId());

			System.out.println("\n--------------------------------------\n");
			
			//Obtengo pago (para recibir el id del pago usar ngrok)
			Payment payment = Payment.getPayment(mercadoPago, "24030552259");
			System.out.println("Pago realizado");
			System.out.println("Caja: " + payment.getPos_id());
			System.out.println("Estado: " + payment.getStatus());
			System.out.println("Total: " +payment.getTransaction_amount());
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	 
}
