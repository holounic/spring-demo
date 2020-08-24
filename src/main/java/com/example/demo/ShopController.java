package com.example.demo;

import com.example.demo.components.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ShopController {
  @Resource(name = "cashierBean")
  private final Cashier CASHIER = new CashierConfig().getCashier();

  private static final Payment EMPTY_PAYMENT = new Payment(0, Payment.PaymentMethod.CARD);
  private static final Cat NULL_CAT = new Cat("Null", 0, new RgbColour(0, 0, 0), 0);
  private static final Bag EMPTY_BAG = new Bag(NULL_CAT, EMPTY_PAYMENT);

  private List<Cat> cats = new LinkedList<>();

  @PostMapping("/sell")
  public Payment sellCat(@RequestBody Cat cat) {
    if (CASHIER.getSum() < cat.getPrice()) {
      return EMPTY_PAYMENT;
    }
    CASHIER.withdraw(cat.getPrice());
    cats.add(cat);
    return new Payment(cat.getPrice(), Payment.PaymentMethod.CASH);
  }

  @GetMapping("/show/{id}")
  public Cat showCat(@PathVariable int id) {
    if (cats.size() <= id) {
      return NULL_CAT;
    }
    return cats.get(id);
  }

  @PostMapping("/buy/{id}")
  public Bag buyCat(@PathVariable int id, @RequestBody Payment payment) {
    if (cats.size() <= id || payment.size < cats.get(id).getPrice()) {
      return EMPTY_BAG;
    }
    Cat cat = cats.get(id);
    Payment change = new Payment(payment.size - cat.getPrice(), Payment.PaymentMethod.CASH);
    CASHIER.put(cat.getPrice());
    return new Bag(cat, change);
  }

}
