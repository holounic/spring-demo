package com.example.demo;

import com.example.demo.components.Bag;
import com.example.demo.components.Cat;
import com.example.demo.components.Payment;
import com.example.demo.components.RgbColour;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

@RestController("/shop")
public class ShopController {
  private static final Payment EMPTY_PAYMENT = new Payment(0, false);
  private static final Cat NULL_CAT = new Cat("Null", 0, new RgbColour(0, 0, 0), 0);
  private static final Bag EMPTY_BAG = new Bag(NULL_CAT, EMPTY_PAYMENT);

  private List<Cat> cats = new LinkedList<>();
  private int balance = 100000;

  @PostMapping("/sell")
  @ResponseBody
  public Payment sellCat(@RequestBody Cat cat) {
    if (balance < cat.getPrice()) {
      return EMPTY_PAYMENT;
    }
    balance -= cat.getPrice();
    cats.add(cat);
    return new Payment(cat.getPrice(), true);
  }

  @GetMapping("/show/{id}")
  @ResponseBody
  public Cat showCat(@PathVariable int id) {
    if (cats.size() <= id) {
      return NULL_CAT;
    }
    return cats.get(id);
  }

  @PostMapping("/buy/{id}")
  @ResponseBody
  public Bag buyCat(@PathVariable int id, @RequestBody Payment payment) {
    if (cats.size() <= id || payment.size < cats.get(id).getPrice()) {
      return EMPTY_BAG;
    }
    Cat cat = cats.get(id);
    Payment change = new Payment(payment.size - cat.getPrice(), true);
    balance += cat.getPrice();
    return new Bag(cat, change);
  }

}
