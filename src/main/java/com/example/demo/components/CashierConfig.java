package com.example.demo.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class CashierConfig {

  @Bean("cashierBean")
  @ApplicationScope
  public Cashier getCashier() {
    return new Cashier();
  }
}
