package com.concurso.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    public String home() {
        return "home/index";
    }
    
}
