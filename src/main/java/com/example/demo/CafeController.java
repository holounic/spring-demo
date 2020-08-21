package com.example.demo;

import com.example.demo.components.Cashier;
import com.example.demo.components.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CafeController {
  private static final int COFFEE_PRICE = 10;

  @Resource(name = "cashierBean")
  private static final Cashier CASHIER = new Cashier();

  @PostMapping("/buy/coffee")
  @ResponseBody
  public Payment buyCoffee() {
    CASHIER.put(COFFEE_PRICE);
    return new Payment(0, true);
  }
}
