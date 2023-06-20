package com.haninz.microservices.shopservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haninz.microservices.shopservice.exceptions.OrderNotFoundException;
import com.haninz.microservices.shopservice.models.Order;
import com.haninz.microservices.shopservice.models.OrderItem;
import com.haninz.microservices.shopservice.feignclients.InventoryProxy;
import com.haninz.microservices.shopservice.feignclients.WalletProxy;
import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.repositories.CartRepository;
import com.haninz.microservices.shopservice.repositories.OrderRepository;
import com.haninz.microservices.shopservice.repositories.OrderItemRepository;

import java.util.*;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private InventoryProxy inventoryProxy;
	
	@Autowired
	private WalletProxy walletProxy;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	@Autowired
	private CartService cartService;
	
	
	public Order createOrder(Long userId)  {
		Cart cart = cartRepo.getByUserId(userId);
		Double userWalletBalance = walletProxy.getUserWalletDto(userId).getWalletBalance();
		List<CartItem> itemsInTheCart= cart.getCartItems();
		List<OrderItem> itemsToOtder = new ArrayList<>();
		Double totalPrice=0.0;
		for(CartItem item: itemsInTheCart) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(item.getProductId());
			orderItem.setPrice(item.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItemRepo.save(orderItem);
			itemsToOtder.add(orderItem);
			
		totalPrice += item.getPrice()*item.getQuantity();
		}
		
			Order userOrder = new Order(totalPrice,"User order",userId,"Pending");
			userOrder.setOrderItems(itemsToOtder);
			Order order = orderRepo.save(userOrder);
			cartService.emptyItemsFromCart(cart.getId());
			return order;
			
		}
	
	
	public String payUserOrder(Long orderId) {
		Optional<Order> order = orderRepo.findById(orderId);
		if(order.isEmpty()) {
			throw new OrderNotFoundException("Order id not found "+ orderId);
		}
		Order theOrder = order.get();
		walletProxy.payUserOrder(theOrder.getId());
		Optional<Order> updatedOrder = orderRepo.findById(orderId);
		Order newOrder=updatedOrder.get();
		
		if(newOrder.getStatus().equals("Paid")) {
			List<OrderItem> orderedItems= newOrder.getOrderItems();
			for(OrderItem item:orderedItems ) {
				inventoryProxy.updateProductQuantity(item.getProductId(), item.getQuantity());
			}
			
//			cartRepo.delete(cart);
//			Cart newCart= new Cart();
//			newCart.setUserId(userId);
//			newCart.setName("User new cart");
//			cartRepo.save(newCart);
			
		}
		return "order is paid";
	}
		
		
			
	
	
	public Order showUserOrder(Long OrderId) {
	Optional<Order> userOrder=orderRepo.findById(OrderId);
	return userOrder.get();
		
	}
	
	
	public void deleteUserOrder(Long OrderId) {
		orderRepo.deleteById(OrderId);
	}
	
	public Order findOrder(Long orderId) {
		Optional<Order> order=orderRepo.findById(orderId);
		if(order.isEmpty()) {
			throw new OrderNotFoundException("Order Id not found "+ orderId);
		}
		return order.get();
		
	}
	
	
	public Order findOrderByUser(Long userId) {
		Order order=orderRepo.findByUserId(userId);
		return order;
		
	}

	public void saveOrder(Order order) {
		orderRepo.save(order);
		
	}
	
	public void removeItemFromOrder(Order order, OrderItem orderItem) {
		List<OrderItem> orderItems = order.getOrderItems();
		orderItems.remove(orderItem);
		order.setOrderItems(orderItems);
		orderRepo.save(order);
		
	}
	
	
	public void addItemToOrder(Order order, OrderItem orderItem) {
		List<OrderItem> orderItems = order.getOrderItems();
		orderItems.add(orderItem);
		order.setOrderItems(orderItems);
		orderRepo.save(order);
		
	}
	
	
	
	
	
	
}


