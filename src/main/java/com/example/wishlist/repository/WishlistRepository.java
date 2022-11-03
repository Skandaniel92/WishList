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
/*
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

 */

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
        String item_price = rs.getString(3);
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

  public void addWish(Wish wish){

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      String sql = "INSERT into wishlist (item_name, item_price, item_link, wishlist_id) \n" +
          "values (?,?,?,?)";
      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setString(1,wish.getItem_name());
      pst.setString(2,wish.getItem_price());
      pst.setString(3, wish.getItem_link());
      pst.setInt(4,wish.getWishlist_id());
      pst.executeUpdate();

    } catch (SQLException e) {
      System.err.println("Cannot connect");
      e.printStackTrace();
    }
  }

  public void deleteWish(int delete_id) {
    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("DELETE FROM wishlist WHERE item_id = ?");

      pst.setInt(1,delete_id);
      pst.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Cannot delete this");
      e.printStackTrace();
    }
  }

  public void deleteWishlist(int delete_id) {
    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      String deleteWishesSql = "DELETE FROM wishlist WHERE wishlist_id = ?";
      String deleteWishlistSql = "DELETE FROM wishlists WHERE wishlist_id = ?";
      PreparedStatement pstWishes = conn.prepareStatement(deleteWishesSql);
      PreparedStatement pstWishlist = conn.prepareStatement(deleteWishlistSql);

      //First delete wishes; then wishlist
      pstWishes.setInt(1, delete_id);
      pstWishes.executeUpdate();

      pstWishlist.setInt(1,delete_id);
      pstWishlist.executeUpdate();
    } catch (SQLException e) {
      System.err.println("Cannot delete this");
      e.printStackTrace();
    }
  }

  public String fetchWishlistNameById(int wishlist_id) {
    String name = "";
    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM wishlists where wishlist_id = ?");
      pst.setInt(1, wishlist_id);
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        name = rs.getString(2);
      }

    } catch (SQLException e) {
      System.err.println("Cannot find wishlist");
      e.printStackTrace();
    }
    return name;
  }

  public Wish fetchWishById(int update_id) {

    Wish wish = new Wish();

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      PreparedStatement pst = conn.prepareStatement("SELECT * FROM wishlist where item_id = ?");
      pst.setInt(1, update_id);
      ResultSet rs = pst.executeQuery();

      while (rs.next()) {
        wish.setItem_id(rs.getInt(1));
        wish.setItem_name(rs.getString(2));
        wish.setItem_price(rs.getString(3));
        wish.setItem_link(rs.getString(4));
        wish.setWishlist_id(rs.getInt(5));
      }

    } catch (SQLException e) {
      System.err.println("Cannot not update wish");
      e.printStackTrace();
    }
    return wish;
  }

  public void updateWish(Wish wish) {

    try {
      Connection conn = DriverManager.getConnection(databaseURL, user, password);
      String sql = "UPDATE wishlist SET item_name=?, item_price=?, item_link=?, wishlist_id=? WHERE item_id = ?";

      PreparedStatement pst = conn.prepareStatement(sql);
      pst.setString(1,wish.getItem_name());
      pst.setString(2,wish.getItem_price());
      pst.setString(3, wish.getItem_link());
      pst.setInt(4, wish.getWishlist_id());
      pst.setInt(5, wish.getItem_id());

      pst.executeUpdate();

    } catch (SQLException e) {
      System.err.println("Cannot update database");
      e.printStackTrace();
    }
  }
}
