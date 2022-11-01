package com.example.wishlist.model;

import java.util.List;

public class WishList {

  private int wishlist_id;
  private String wishlist_name;

  public WishList() {  }

  public WishList(int wishlist_id, String wishlist_name) {
    this.wishlist_id = wishlist_id;
    this.wishlist_name = wishlist_name;
  }

  public int getWishlist_id() {
    return wishlist_id;
  }

  public void setWishlist_id(int wishlist_id) {
    this.wishlist_id = wishlist_id;
  }

  public String getWishlist_name() {
    return wishlist_name;
  }

  public void setWishlist_name(String wishlist_name) {
    this.wishlist_name = wishlist_name;
  }
}
