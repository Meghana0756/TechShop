package com.techshop.TechShop.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techshop.TechShop.dao.OrdersService;
import com.techshop.TechShop.entity.Customers;
import com.techshop.TechShop.entity.Orders;
import com.techshop.TechShop.util.DBConnUtil;
import com.techshop.TechShop.util.DBPropertyUtil;
import com.techshop.TechShop.entity.OrderDetails;

public class OrdersServiceImpl implements OrdersService{
	
private Connection connection;
	
	public OrdersServiceImpl() {
        this.connection = DBConnUtil.getConnection(DBPropertyUtil.getConnectionString());	
    }
	
	
	@Override
	public double calculateTotalAmount(int orderId) {
        double totalAmount = 0.0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT TotalAmount FROM orders WHERE OrderID = ?")) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    totalAmount = resultSet.getDouble("TotalAmount");
                } else {
                    System.out.println("Order not found with ID: " + orderId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalAmount;
    }
	
	
	@Override
	public void getOrderDetails(int orderId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM orders WHERE OrderID = ?")) {
            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            	if (resultSet.next()) {
                    System.out.println("Order Details for Order ID: " + orderId);
                    System.out.println("Customer ID: " + resultSet.getInt("CustomerID"));
                    System.out.println("Order Date: " + resultSet.getDate("OrderDate"));
                    System.out.println("Total Amount: $" + resultSet.getDouble("TotalAmount"));
                    System.out.println("Order Status: " + resultSet.getString("OrderStatus"));
                } else {
                    System.out.println("Order not found with ID: " + orderId);
                }
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	@Override
	public void updateOrderStatus(int orderId, String newStatus) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE orders SET Status = ? WHERE OrderID = ?")) {
            preparedStatement.setString(1, newStatus);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order status updated successfully.");
            } else {
                System.out.println("Failed to update order status. Order not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	 @Override
	    public void cancelOrder(int orderId) {
	        try (PreparedStatement preparedStatement = connection.prepareStatement(
	                "DELETE FROM orders WHERE OrderID = ?")) {
	            preparedStatement.setInt(1, orderId);

	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Order canceled successfully.");
	            } else {
	                System.out.println("Failed to cancel order. Order not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
