package com.techshop.TechShop.dao;

public interface OrderDetailsService {

	double calculateSubtotal(int orderDetailId);

	void getOrderDetailInfo(int orderDetailId);

	void updateQuantity(int orderDetailId, int newQuantity);

	void addDiscount(int orderDetailId, double discountAmount);

}
