package com.example.demo;

import com.example.demo.components.Cashier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class CashierController {

  @Resource(name = "cashierBean")
  private Cashier CASHIER = new Cashier();

  @GetMapping(value = "/balance")
  public int getBalance() {
    return CASHIER.getSum();
  }

}
