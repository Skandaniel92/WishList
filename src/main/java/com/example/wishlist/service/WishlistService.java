package com.example.wishlist.service;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

  WishlistRepository wishlistRepository;

  public WishlistService(WishlistRepository wr){
    wishlistRepository = wr;
  }


  public List<Wish> fetchAllWishes() {
  return wishlistRepository.fetchAllWishes();
  }

  public List<WishList> fetchAllWishlists() {
    return wishlistRepository.fetchAllWishlists();
  }


  public List<Wish> findWishesById(int wishlist_id) {
    return wishlistRepository.fetchWishesById(wishlist_id);
  }

  public void addWishlist(WishList wishList) {
    wishlistRepository.addWishlist(wishList);
  }

  public void addWish(Wish wish) {
    wishlistRepository.addWish(wish);
  }

  public void deleteWishById(int delete_id) {
    wishlistRepository.deleteWish(delete_id);
  }

  public void deleteWishlistById(int delete_id) {
    wishlistRepository.deleteWishlist(delete_id);
  }

  public String findWishlistNameById(int wishlist_id) {
    return wishlistRepository.fetchWishlistNameById(wishlist_id);
  }
}
