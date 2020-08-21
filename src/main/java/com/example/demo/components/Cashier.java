package com.example.demo.components;

import org.springframework.stereotype.Component;

@Component
public class Cashier {
  private int sum = 10000;

  public Cashier() {}

  public int getSum() {
    return sum;
  }

  public void put(int sum) {
    this.sum += sum;
  }

  public void withdraw(int sum) {
    this.sum -= sum;
  }
}
