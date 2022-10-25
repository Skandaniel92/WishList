package com.example.wishlist.repository;

import com.example.wishlist.model.Wish;
import org.springframework.stereotype.Repository;
import org.thymeleaf.preprocessor.PreProcessor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {

  private final String databaseURL = "jdbc:mysql://localhost:3306/christmas_wishlist";
  private final String user = "christmas_wishlist_user";
  private final String password = "1234";

  public List<Wish> fetchAll() {
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

        wishes.add(new Wish(item_id, item_name, item_price, item_link));
      }
    } catch (SQLException e) {
      System.err.println("Cannot connect");
      e.printStackTrace();
    }
    return wishes;
  }
}
