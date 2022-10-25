package com.example.wishlist.model;

public class Wish {
  private int item_id;
  private String item_name;
  private double item_price;
  private String item_link;

  public Wish() {}

  public Wish(int item_id, String item_name, double item_price, String item_link) {
    this.item_id = item_id;
    this.item_name = item_name;
    this.item_price = item_price;
    this.item_link = item_link;
  }

  public int getItem_id() {
    return item_id;
  }

  public void setItem_id(int item_id) {
    this.item_id = item_id;
  }

  public String getItem_name() {
    return item_name;
  }

  public void setItem_name(String item_name) {
    this.item_name = item_name;
  }

  public double getItem_price() {
    return item_price;
  }

  public void setItem_price(double item_price) {
    this.item_price = item_price;
  }

  public String getItem_link() {
    return item_link;
  }

  public void setItem_link(String item_link) {
    this.item_link = item_link;
  }

  @Override
  public String toString() {
    return "Wish{" +
        "item_id=" + item_id +
        ", item_name='" + item_name + '\'' +
        ", item_price=" + item_price +
        ", item_link='" + item_link + '\'' +
        '}';
  }
}
