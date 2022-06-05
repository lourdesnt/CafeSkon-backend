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

/**
 * Controlador para pedidos (Order)
 * 
 * @author Lourdes Navarro
 *
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

	/**
	 * Repositorio de pedidos (tipo OrderRepository)
	 */
	@Autowired
	private OrderRepository orderRepository;

	/**
	 * Repositorio de productos-pedidos (tipo ProductOrderJoinRepository)
	 */
	@Autowired
	private ProductOrderJoinRepository prodOrderRepository;
	
	/**
	 * Repositorio de usuarios (tipo CafeUserRepository)
	 */
	@Autowired
	private CafeUserRepository userRepository;
	
	/**
	 * Repositorio de productos (tipo ProductRepository)
	 */
	@Autowired
	private ProductRepository productRepository;

	/**
	 * Método GET para obtener todos los pedidos (con los atributos del modelo DTO de OrderListDto)
	 * @return respuesta de la llamada
	 */
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
	
	/**
	 * Método GET para obtener un pedido por su ID (con los atributos del modelo DTO de OrderDto)
	 * @param id ID del pedido
	 * @return respuesta de la llamada
	 */
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

	/**
	 * Método POST para crear un nuevo pedido
	 * @param order Nuevo pedido
	 * @return respuesta de la llamada
	 */
	@PostMapping("/new")
	public ResponseEntity<Order> createOrder(@RequestBody final OrderDto order) {
		try {
			Order _order = new Order(order);
			_order.setCustomer(userRepository.findById(order.getCustomerId()).get());
			Order savedOrder = orderRepository.save(_order);
			order.getProductMap().forEach((id,q) -> {
				ProductOrderJoin prodOrder = new ProductOrderJoin();
				prodOrder.setProduct(productRepository.findById(id).get());
				prodOrder.setOrder(_order);
				prodOrder.setQuantity(q);
				System.out.println(prodOrder.getQuantity());
				prodOrderRepository.save(prodOrder);
			});
			orderRepository.flush();
			return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Método DELETE para eliminar un pedido
	 * @param id ID del pedido a eliminar
	 * @return respuesta de la llamada
	 */
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
