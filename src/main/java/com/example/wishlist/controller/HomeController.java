package com.example.wishlist.controller;

import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  WishlistRepository wishlistRepository;

  public HomeController(WishlistRepository wr) {
    wishlistRepository = wr;
  }

  @GetMapping("")
  public String index(Model model) {
    model.addAttribute("wishes", wishlistRepository.fetchAll());
    return "index";
  }
}
