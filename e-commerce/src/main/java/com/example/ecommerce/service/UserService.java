package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.SignupDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.enums.OrderStatus;
import com.example.ecommerce.enums.UserRole;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	
	public UserDto createUser(SignupDto signupDto) {
		
		User user = new User();
		user.setName(signupDto.getName());
		user.setEmail(signupDto.getEmail());
		user.setUserRole(UserRole.USER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupDto.getPassword()));
		User createdUser = userRepository.save(user);
		
		Order order = new Order();
		order.setAmount(0L);
		order.setTotalAmount(0L);
		order.setDiscount(0L);
		order.setUser(createdUser);
		order.setOrderStatus(OrderStatus.Pending);
		orderRepository.save(order);
		
		UserDto userDto = new UserDto();
		userDto.setId(createdUser.getId());
		userDto.setName(createdUser.getName());
		userDto.setEmail(createdUser.getEmail());
		userDto.setUserRole(createdUser.getUserRole());
		return userDto;
	}
	
	
	public boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email) != null;
	}
}
