package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
public class BasicController {

  public static class Response {
    public String string;
    public int integer;

    Response(String string, int integer) {
      this.string = string;
      this.integer = integer;
    }
  }

  public static class Request {
    public int integer = 10;
  }

  @GetMapping("/test")
  @ResponseBody
  public Response exampleGetRequest() {
    return new Response("Hello", 10);
  }

  @PostMapping("/square")
  public Response anotherExampleGetRequest(@RequestBody Request request) {
    return new Response("squared", request.integer * request.integer);
  }
}
