package com.example.demo.components;

import org.springframework.stereotype.Component;

@Component
public class Cashier {
  private int sum = 10000;

  public Cashier() {}

  public int getSum() {
    return sum;
  }

  public void put(int sum_) {
    sum += sum_;
  }

  public void withdraw(int sum_) {
    sum -= sum_;
  }
}
