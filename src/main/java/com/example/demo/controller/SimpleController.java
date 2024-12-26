package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class SimpleController {

    @Data
    @AllArgsConstructor
    class User {
        private String name;
        private String email;

    }

    @GetMapping("/example")
    public String index(Model model) {
        model.addAttribute("name", "Ivan Ivanov");

        List<String> departments = Arrays.asList("South", "West", "Center");
        model.addAttribute("departments", departments);

        User user = new User("Ivan", "Ivan@gamil.com");
        model.addAttribute("user", user);
        return "example/index";
    }

    @PostMapping("/example/save")
    public String save(@ModelAttribute("user") User user) {
        System.out.println("Saving user = " + user);
        return "redirect:/example";
    }

    @GetMapping("/example/footer")
    public String footer() {
        return "example/fragments/footer :: footer";
    }

    @GetMapping("/example/header")
    public String header() {
        return "example/fragments/header :: header";
    }

}
