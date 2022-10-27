package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
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

//  @GetMapping("/showWishes")
//  public String showWishes(Model model) {
//    model.addAttribute("wishes", wishlistService.fetchAllWishes());
//    return "showWishes";
//  }


  @GetMapping("/makeWish")
  public String showMakeWish(){
    return "makeWish";
  }

  @GetMapping("/showWishes/{wishlist_id}")
  public String showWishesById(@PathVariable("wishlist_id") int wishlist_id, Model model){
    model.addAttribute("wishes",wishlistService.findWishesById(wishlist_id));
    return "/showWishes";
  }

  @GetMapping("/showCreateWishList")
  public String createWishList(){
    return "createWishList";
  }

  @PostMapping("/createWishList")
  public String createWishList(@ModelAttribute WishList wishList){
    wishlistService.addWishlist(wishList);
    return "redirect:/editWishes";
  }

  @GetMapping("/editWishes")
  public String showEditWish(Model model){
    model.addAttribute("wishlists", wishlistService.fetchAllWishlists());
    return "editWishes";
  }

  @PostMapping("/addWish/{wishlist_id}")
  public String makeWish(@ModelAttribute Wish wish){
    wishlistService.makeAWish(wish);

    return "redirect:/addWish/"+wish.getWishlist_id();
  }

  @GetMapping("/addWish/{wishlist_id}")
  public String showAddWish(@PathVariable("wishlist_id") int wishlist_id, Model model){
    model.addAttribute("wishlist", wishlist_id);
    model.addAttribute("wishes",wishlistService.findWishesById(wishlist_id));
    return "addWish";
  }

}
