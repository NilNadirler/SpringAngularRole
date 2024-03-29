package com.example.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.WishListDto;
import com.example.ecommerce.service.WishListServiceImpl;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(maxAge = 3600)
public class WishListController {
	
	private final WishListServiceImpl wishListService;
	
	
	public WishListController(WishListServiceImpl wishListService) {
		super();
		this.wishListService = wishListService;
	}

    @PostMapping("/wishList")
	public ResponseEntity<?> addProductToWishList(@RequestBody WishListDto wishListDto){
		
		WishListDto wishListDto1 = wishListService.addProductToWishList(wishListDto);
		if(wishListDto ==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		 return ResponseEntity.status(HttpStatus.CREATED).body(wishListDto1);
		}
    
    @GetMapping("/wishList/{userId}")
	public ResponseEntity<?> getWishListByUserId(@PathVariable Long userId){
    	
    	return ResponseEntity.ok(wishListService.getWishListByUserId(userId));
		
    }
 
 
		
}




