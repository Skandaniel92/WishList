package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.model.WishList;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

  @GetMapping("/showWishes")
  public String showWishes(Model model) {
    model.addAttribute("wishes", wishlistService.fetchAll());
    return "showWishes";
  }


  @GetMapping("/makeWish")
  public String showMakeWish(){
    return "makeWish";
  }

  @GetMapping("/showCreateWishList")
  public String createWishList(){
    return "createWishList";
  }

  @PostMapping("/createWishList")
  public String createWishList(@ModelAttribute WishList wishList){
    return "redirect:/editWishes";
  }

  @GetMapping("/editWishes")
  public String showEditWish(Model model){
    model.addAttribute("wishes", wishlistService.fetchAll());
    return "editWishes";
  }

  @PostMapping("/makeWish")
  public String makeWish(@ModelAttribute Wish wish){
    wishlistService.makeAWish(wish);
    return "redirect:/showWishes";
  }


}
