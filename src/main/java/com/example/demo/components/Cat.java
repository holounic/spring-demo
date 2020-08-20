package com.example.demo.components;

public class Cat {
  private final String name;
  private int age;
  private final RgbColour colour;
  private final int price;

  public String getName() {
    return name.length() > 20 ? "Too long name" : name;
  }

  public int getAge() {
    return age;
  }

  public RgbColour getColour() {
    return colour;
  }

  public int getPrice() {
    return price;
  }

  public Cat(String name, int age, RgbColour colour, int price) {
    this.name = name;
    this.age = age;
    this.colour = colour;
    this.price = price;
  }
}
