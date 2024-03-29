package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.WishListDto;
import com.example.ecommerce.entities.Product;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.entities.WishList;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.WishListRepository;

@Service
public class WishListServiceImpl {

	private final UserRepository userRepository;	
	private final ProductRepository productRepository;
	private final WishListRepository wishListRepository;
	public WishListServiceImpl(UserRepository userRepository, ProductRepository productRepository,
			WishListRepository wishListRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.wishListRepository = wishListRepository;
	}
	
	public WishListDto addProductToWishList(WishListDto wishListDto) {
		Optional<Product> optionalProduct=productRepository.findById(wishListDto.getProductId());
		Optional<User> optionalUser = userRepository.findById(wishListDto.getUserId());
		
		boolean isExist =wishListRepository.existsByUserIdAndProductId(wishListDto.getUserId(), wishListDto.getProductId());
		if(!isExist) 
		{
			if(optionalProduct.isPresent() && optionalProduct.isPresent()) {
				WishList wishList = new WishList();
				wishList.setProduct(optionalProduct.get());
				wishList.setUser(optionalUser.get());
				
				return wishListRepository.save(wishList).getDto();
			}
		}
		
		return null;
		
	}
	
	public List<WishListDto> getWishListByUserId(Long userId){
	
		List<WishList> wishList = wishListRepository.findAllByUserId(userId);
		return wishList.stream().map(WishList::getDto).collect(Collectors.toList());
		
	}
	
	
}
