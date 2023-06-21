package com.haninz.microservices.shopservice.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haninz.microservices.shopservice.DTO.OrderItemDto;
import com.haninz.microservices.shopservice.DTO.PaymentDto;
import com.haninz.microservices.shopservice.feignclients.InventoryProxy;
import com.haninz.microservices.shopservice.feignclients.WalletProxy;
import com.haninz.microservices.shopservice.models.Cart;
import com.haninz.microservices.shopservice.models.CartItem;
import com.haninz.microservices.shopservice.models.Order;
import com.haninz.microservices.shopservice.models.OrderItem;
import com.haninz.microservices.shopservice.repositories.OrderRepository;
import com.haninz.microservices.shopservice.services.CartService;
import com.haninz.microservices.shopservice.services.OrderItemService;
import com.haninz.microservices.shopservice.services.OrderService;
//import com.haninz.microservices.shopservice.services.OrderService;


@RestController
@RequestMapping("/order-api")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private InventoryProxy inventoryProxy;
	
	@Autowired
	private WalletProxy walletProxy;
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	
	@PostMapping("/orders/{userId}")
	public Order placeOrder(@PathVariable Long userId)  {
		Order order =orderService.createOrder(userId);
		
		 return order;
	}
	
	
	@PostMapping("/orders/{orderId}/pay")
	public Order PayTheOrder(@PathVariable Long orderId) throws Exception {
		Order order=orderService.payUserOrder(orderId);
		
		 return order;
	}
	
	
	@GetMapping("/orders/{orderId}")
	public Order showOrder(@PathVariable Long orderId) {
		return orderService.showUserOrder(orderId);
	}
	
	@PutMapping("/orders/{orderId}/cancel")
	public Order cancelOrder(@PathVariable Long orderId) {
		Order order = orderService.findOrder(orderId);
		order.setStatus("Cancelled");
		orderService.saveOrder(order);
		Long userId= order.getUserId();
		Cart cart = cartService.getCartByUserId(userId);
		cartService.emptyItemsFromCart(cart.getId());
		return order;
	}
	

	
	@GetMapping("/orders/{orderId}/items")
	public List<OrderItem> showOrderItems(@PathVariable Long orderId) {
		Order order=orderService.showUserOrder(orderId);
		return order.getOrderItems();
	}
	
	@GetMapping("/orders/{orderId}/itemsDto")
	public List<OrderItemDto> sendOrderItems(@PathVariable Long orderId) {
		Order order=orderService.showUserOrder(orderId);
		List<OrderItemDto> orderitemsDto = new ArrayList();
		List<OrderItem> orderItems= order.getOrderItems();
		for(OrderItem orderItem: orderItems ) {
			OrderItemDto dtoItem = new OrderItemDto();
			dtoItem.setProductId(orderItem.getProductId());
			dtoItem.setQuantity(orderItem.getQuantity());
			orderitemsDto.add(dtoItem);
			
		}
		return orderitemsDto;
		
	}
	
	
	 @PutMapping("/orders/{orderId}/status")
	    public Order updateOrderStatusToPaid(@PathVariable Long orderId) {
		 Order order = orderService.findOrder(orderId);
		 order.setStatus("Paid");
		 orderService.saveOrder(order);
		 return order;
		 
	        
	    }
	 // if paid no
	 @PostMapping("/orders/{orderId}/remove/{orderItemId}")
		public Order removeFromOrder(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) throws Exception {
			Order order = orderService.findOrder(orderId);
			if(order.getStatus().equals("Pending")) {
				OrderItem orderItem = orderItemService.getOrderItem(orderItemId);
				
				orderService.removeItemFromOrder(order, orderItem);
				Double amountToRemove= orderItem.getPrice()*orderItem.getQuantity();
				order.setTotal(order.getTotal()-amountToRemove);
				
				orderService.saveOrder(order);
				return order;
				
			}
			throw new Exception("Order can't be adjusted");
			
		}
	 
	 
	 @PostMapping("/orders/{orderId}/add/{orderItemId}")
		public Order addItemToOrder(@PathVariable("orderId") Long orderId, @PathVariable("orderItemId") Long orderItemId) throws Exception {
			Order order = orderService.findOrder(orderId);
			if(order.getStatus().equals("Pending")) {
				OrderItem orderItem = orderItemService.getOrderItem(orderItemId);
				
				orderService.addItemToOrder(order, orderItem);
				Double amountToRemove= orderItem.getPrice()*orderItem.getQuantity();
				order.setTotal(order.getTotal()-amountToRemove);
				
				orderService.saveOrder(order);
				return order;
				
			}
			throw new Exception("Order can't be adjusted");
			
		}
	 
	 
	 

}
