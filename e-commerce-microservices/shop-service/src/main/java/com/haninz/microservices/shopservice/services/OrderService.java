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
	
	
	public Order findOrder(Long orderId) {
		Optional<Order> order=orderRepo.findById(orderId);
		if(order.isEmpty()) {
			throw new OrderNotFoundException("Order Id not found "+ orderId);
		}
		return order.get();
		
	}
	
	
	public Order payUserOrder(Long orderId) throws Exception {
		Order theOrder = findOrder(orderId);
		if(theOrder.getStatus().equals("Pending")) {
		Long userId = theOrder.getUserId();
		Double walletBalance = walletProxy.getUserWalletDto(userId).getWalletBalance();
		Double orderTotal= theOrder.getTotal();
		if(orderTotal <= walletBalance  && walletBalance - orderTotal >= 0) {
		walletProxy.payUserOrder(theOrder.getId());
		theOrder.setStatus("Paid");
		orderRepo.save(theOrder);
			List<OrderItem> orderedItems= theOrder.getOrderItems();
			for(OrderItem item:orderedItems ) {
				inventoryProxy.updateProductQuantity(item.getProductId(), item.getQuantity());
				
			}
			
		
          
		}
		else {
			theOrder.setStatus("Rejected");
			orderRepo.save(theOrder);
			
		}
		}
		else {
			throw new Exception("Can't pay for this order!");
		}
		return theOrder;
		
	}
		
		
			
	
	
	public Order showUserOrder(Long OrderId) {
	Optional<Order> userOrder=orderRepo.findById(OrderId);
	return userOrder.get();
		
	}
	
	
	public void deleteUserOrder(Long OrderId) {
		orderRepo.deleteById(OrderId);
	}
	
	
	public void saveOrder(Order order) {
		orderRepo.save(order);
		
	}
	
	public void removeItemFromOrder(Order order, OrderItem orderItem) {
		List<OrderItem> orderItems = order.getOrderItems();
		orderItems.remove(orderItem);
		order.setOrderItems(orderItems);
		Double amountToRemove= orderItem.getPrice()*orderItem.getQuantity();
		order.setTotal(order.getTotal()-amountToRemove);
		orderRepo.save(order);
	}
	
	
	
	
	
	
	
	public List<Order> getAllUserOrders(Long userId){
		List<Order> orders= orderRepo.findByUserId(userId);
		return orders;
	}
	
	
	
	
	
	
}


