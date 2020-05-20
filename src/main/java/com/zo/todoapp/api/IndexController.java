package com.zo.todoapp.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

  @GetMapping
  public String welcome() {
    return "<h1> Welcome etc </h1>";
  }

}
