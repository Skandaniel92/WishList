package com.example.wishlist.controller;

import com.example.wishlist.service.model.Wish;
import com.example.wishlist.service.model.WishList;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

  WishlistService wishlistService;

  public HomeController(WishlistService ws) {
    wishlistService = ws;
  }

  @GetMapping("")
  public String index() {
    return "index";
  }

  @GetMapping("/showWishes/{wishlist_id}")
  public String showWishesById(@PathVariable("wishlist_id") int wishlist_id, Model model) {
    model.addAttribute("wishes", wishlistService.findWishesById(wishlist_id));
    model.addAttribute("newWishId", wishlist_id);
    return "/showWishes";
  }

  @GetMapping("/showCreateWishList")
  public String createWishList() {
    return "createWishList";
  }

  @PostMapping("/createWishList")
  public String createWishList(@ModelAttribute WishList wishList) {
    wishlistService.addWishlist(wishList);
    return "redirect:/editWishes";
  }

  @GetMapping("/editWishes")
  public String showEditWish(Model model) {
    model.addAttribute("wishlists", wishlistService.fetchAllWishlists());
    return "editWishes";
  }


  @PostMapping("/addWish/{wishlist_id}")
  public String makeWish(@ModelAttribute Wish wish) {
    wishlistService.addWish(wish);
    return "redirect:/addWish/" + wish.getWishlist_id();
  }

  @GetMapping("/addWish/{wishlist_id}")
  public String showAddWish(@PathVariable("wishlist_id") int wishlist_id, Model model) {
    model.addAttribute("wishlist", wishlist_id);
    model.addAttribute("wishes", wishlistService.findWishesById(wishlist_id));
    model.addAttribute("wishlist_name", wishlistService.findWishlistNameById(wishlist_id));
    return "addWish";
  }
  // Delete wish
  @GetMapping("/deleteWish/{wishlist_id}/{item_id}")
  public String showDeleteWish(@PathVariable("item_id") int delete_id, @PathVariable("wishlist_id") int wishlist_id) {
    wishlistService.deleteWishById(delete_id);
    return "redirect:/addWish/" + wishlist_id;
  }

  // Delete wishlist
  @GetMapping("/deleteWishlist/{wishlist_id}")
  public String showDeleteWishlist(@PathVariable("wishlist_id") int delete_id) {
    wishlistService.deleteWishlistById(delete_id);
    return "redirect:/editWishes/";
  }

  @GetMapping("/updateWish/{item_id}")
  public String showUpdateWish(@PathVariable("item_id") int update_id, Model model) {
    model.addAttribute("wish", wishlistService.fetchWishById(update_id));
    return "updateWish";
  }

  @PostMapping("/updateWish")
  public String updateWish(@ModelAttribute Wish wish) {
    wishlistService.updateWish(wish);
    int id = wish.getWishlist_id();
    return "redirect:/addWish/" + id;
  }

  // Guest pages

  @GetMapping("/showWishListsGuest")
  public String showWishListsGuest(Model model) {
    model.addAttribute("wishlists", wishlistService.fetchAllWishlists());
    return "showWishListsGuest";
  }

  @GetMapping("/showWishListGuest/{wishlist_id}")
  public String showWishListGuest(@PathVariable("wishlist_id") int wishlist_id, Model model) {
    model.addAttribute("wishlist", wishlist_id);
    model.addAttribute("wishes", wishlistService.findWishesById(wishlist_id));
    model.addAttribute("wishlist_name", wishlistService.findWishlistNameById(wishlist_id));
    return "showWishListGuest";
  }

}
