package com.example.wishlist.controller;

import com.example.wishlist.model.Wish;
import com.example.wishlist.repository.WishlistRepository;
import com.example.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
  WishlistRepository wishlistRepository;
WishlistService

  public HomeController(WishlistRepository wr) {
    wishlistRepository = wr;
  }

  @GetMapping("")
  public String index() {
    return "index";
  }

  @GetMapping("/showWishes")
  public String showWishes(Model model) {
    model.addAttribute("wishes", wishlistRepository.fetchAll());
    return "showWishes";
  }


  @GetMapping("/makeWish")
  public String showMakeWish(Model model){
    Wish wish = new Wish();
    model.addAttribute("wish", wish);
    return "wish";
  }

  @PostMapping("/makeWish")
  public String makeWish(@ModelAttribute ("wish") Wish wish){
    wishService
  }


}
