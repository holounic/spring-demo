package com.example.demo.components;

public class Payment {

  public enum PaymentMethod {
    CASH("CASH"), CARD("CARD");

    private String name;
    PaymentMethod(String name) {
      this.name = name;
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

  public final int size;
  public final PaymentMethod method;

  public Payment(int size, PaymentMethod method) {
    this.size = size;
    this.method = method;
  }
}
