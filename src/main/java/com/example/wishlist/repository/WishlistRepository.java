package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {

  @Value("${spring.datasource.url}")
  private String databaseURL;
  @Value("${spring.datasource.username}")
  private String user;
  @Value("${spring.datasource.password}")
  private String password;

  //private final String databaseURL = "jdbc:mysql://localhost:3306/christmas_wishlist";
  //private final String user = "christmas_wishlist_user";
  //private final String password = "1234";

  public List<Wish> fetchAllWishes() {
    ArrayList<Wish> wishes = new ArrayList<>();

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM wishlist");
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int item_id = rs.getInt(1);
        String item_name = rs.getString(2);
        double item_price = rs.getDouble(3);
        String item_link = rs.getString(4);
        int wishlist_id = rs.getInt(5);

        wishes.add(new Wish(item_id, item_name, item_price, item_link, wishlist_id));
      }
    } catch (SQLException e) {
      System.err.println("Cannot connect");
      e.printStackTrace();
    }
    return wishes;
  }

  public List<WishList> fetchAllWishlists() {
    ArrayList<WishList> wishLists = new ArrayList<>();

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM wishlists");
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int wishlist_id = rs.getInt(1);
        String wishlist_name = rs.getString(2);

        WishList wishList = new WishList(wishlist_id, wishlist_name);
        wishLists.add(wishList);

      }
    } catch (SQLException e) {
      System.err.println("Cannot connect");
      e.printStackTrace();
    }
    return wishLists;
  }

  public List<Wish> fetchWishesById(int wishlist_id) {
    ArrayList<Wish> wishes = new ArrayList<>();

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM wishlist where wishlist_id = ?");
      pst.setInt(1, wishlist_id);
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        int item_id = rs.getInt(1);
        String item_name = rs.getString(2);
        double item_price = rs.getDouble(3);
        String item_link = rs.getString(4);
        //wishlist_id = rs.getInt(5);

        wishes.add(new Wish(item_id, item_name, item_price, item_link, wishlist_id));
      }
    } catch (SQLException e) {
      System.err.println("Cannot connect");
      e.printStackTrace();
    }
    return wishes;
  }

  public void addWishlist(WishList wishList) {
    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      String sql = "INSERT INTO wishlists (wishlist_name) VALUES (?)";
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setString(1, wishList.getWishlist_name());
      pst.executeUpdate();

    } catch (SQLException e) {
      System.err.println("Cannot add wishlist");
      e.printStackTrace();
    }
  }
}
