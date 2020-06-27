package com.lampa.alfabattle.second.controllers;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("health")
    public Health getHea() {
        return new Health();
    }

    @Data
    private static class Health {
        private String status = "UP";
    }
}
