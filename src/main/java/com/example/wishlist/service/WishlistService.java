package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

  WishlistRepository wishlistRepository;

  public WishlistService(WishlistRepository wr){
    wishlistRepository = wr;
  }


  public List<Wish> fetchAll() {
  return wishlistRepository.fetchAll();
  }

  public void makeAWish(Wish wish) {

  }
}
