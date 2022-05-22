package com.example.cafeskon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeskon.model.Order;
import com.example.cafeskon.model.OrderDto;
import com.example.cafeskon.model.OrderListDto;
import com.example.cafeskon.model.ProductOrderJoin;
import com.example.cafeskon.repository.CafeUserRepository;
import com.example.cafeskon.repository.OrderRepository;
import com.example.cafeskon.repository.ProductOrderJoinRepository;
import com.example.cafeskon.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductOrderJoinRepository prodOrderRepository;
	
	@Autowired
	private CafeUserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/all")
	public ResponseEntity<List<OrderListDto>> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderListDto> orderList = new ArrayList<OrderListDto>();
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			orders.stream().forEach(o -> {
				Map<String, Integer> productMap = prodOrderRepository.findByOrder(o).stream().collect(Collectors.toMap((ProductOrderJoin p) -> p.getProduct().getName(), (ProductOrderJoin p) -> p.getQuantity()));
				orderList.add(new OrderListDto(o, productMap));
			});
			return new ResponseEntity<>(orderList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Integer id) {
		Optional<Order> order = orderRepository.findById(id);
		OrderDto orderDetails = new OrderDto();
		if (!order.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			orderDetails.setId(order.get().getId());
			orderDetails.setCustomerId(order.get().getCustomer().getUsername());
			orderDetails.setFirstName(order.get().getFirstName());
			orderDetails.setLastName(order.get().getLastName());
			orderDetails.setAddress(order.get().getAddress());
			orderDetails.setPostalCode(order.get().getPostalCode());
			orderDetails.setPhone(order.get().getPhone());
			orderDetails.setPayment(order.get().getPayment());
			orderDetails.setOrderDate(order.get().getOrderDate());
			Map<Integer, Integer> productMap = prodOrderRepository.findByOrder(order.get()).stream().collect(Collectors.toMap((ProductOrderJoin p) -> p.getProduct().getId(), (ProductOrderJoin p) -> p.getQuantity()));
			HashMap<Integer, Integer> productHashMap = new HashMap<Integer, Integer>(productMap);
			orderDetails.setProductMap(productHashMap);
			return new ResponseEntity<>(orderDetails, HttpStatus.OK);
		}
	}

	@PostMapping("/new")
	public ResponseEntity<Order> createOrder(@RequestBody final OrderDto order) {
		try {
//			productsOrder.stream().forEach(p -> {
//				System.out.println(p.getOrder().getFirstName());
//				orderRepository.save(p.getOrder());
//				prodOrderRepository.save(p);
//			});
//			prodOrderRepository.flush();
			//System.out.println(order.getCustomerId());
			//System.out.println(order.getProductMap());
			Order _order = new Order(order);
			_order.setCustomer(userRepository.findById(order.getCustomerId()).get());
			Order savedOrder = orderRepository.save(_order);
			//System.out.println(_order.getCustomer());
			order.getProductMap().forEach((id,q) -> {
				ProductOrderJoin prodOrder = new ProductOrderJoin();
				prodOrder.setProduct(productRepository.findById(id).get());
				prodOrder.setOrder(_order);
				prodOrder.setQuantity(q);
				System.out.println(prodOrder.getQuantity());
				prodOrderRepository.save(prodOrder);
			});
			//System.out.println(order.getProductMap());
			orderRepository.flush();
			return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") Integer id) {
		try {
			prodOrderRepository.deleteAll(prodOrderRepository.findByOrder(orderRepository.findById(id).get())); //deleteallbyorder??
			orderRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
