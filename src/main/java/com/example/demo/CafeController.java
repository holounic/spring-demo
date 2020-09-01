package com.example.demo;

import com.example.demo.components.Cashier;
import com.example.demo.components.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class CafeController {
  private final int COFFEE_PRICE = 10;

  @Resource(name = "cashierBean")
  private final Cashier CASHIER = new Cashier();

  /**
   * @param payment representation of the json {"size": <int>, "method": <String representation of
   *                {@see Payment.PaymentMethod}>, "type": <String>}. Note that the {@see Payment}
   *                has no "type" field, but Spring will convert it to Payment instance;
   * @return change
   */
  @PostMapping("/buy/coffee")
  public Payment buyCoffee(@RequestBody Payment payment) {
    if (payment.size < COFFEE_PRICE) {
      return payment;
    }
    CASHIER.put(COFFEE_PRICE);
    int change = payment.size - COFFEE_PRICE;
    return new Payment(change, Payment.PaymentMethod.CASH);
  }
}
