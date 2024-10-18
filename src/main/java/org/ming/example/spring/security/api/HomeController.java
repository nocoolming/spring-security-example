package org.ming.example.spring.security.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    String home(){
        return "Spring Security 6.x example.";
    }
}
