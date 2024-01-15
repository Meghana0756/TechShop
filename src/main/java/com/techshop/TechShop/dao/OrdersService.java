package com.techshop.TechShop.dao;



public interface OrdersService {

	void cancelOrder(int orderId);

	void updateOrderStatus(int orderId, String newStatus);

	void getOrderDetails(int orderId);

	double calculateTotalAmount(int orderId);
	


}
