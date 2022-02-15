package com.lewis.in28minsrestfulwebservices.controller;

import com.lewis.in28minsrestfulwebservices.model.HelloObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello")
    public String sayHello(@RequestHeader(value = "Accept-Language", required = false)Locale locale){
        return messageSource.getMessage("greetings.hello", null, locale);
    }
    @GetMapping("/hello-object")
    public HelloObject sayHelloWithObject(){
        return new HelloObject("Hello World!");
    }
    @GetMapping("/hello/{name}")
    public HelloObject sayHelloWithPathVar(@PathVariable String name){
        return new HelloObject(String.format("Hello World %s!", name));
    }
}
