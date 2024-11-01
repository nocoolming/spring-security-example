package org.ming.example.spring.security.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("admin")
    String admin() {
        return "admin";
    }
}
