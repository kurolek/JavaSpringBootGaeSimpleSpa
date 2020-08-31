package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String sayHello () {
        return "Hello GAE!";
    }
}
