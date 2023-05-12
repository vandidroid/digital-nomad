package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.service.UnsplashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://travel.dinodev.hu", "http://localhost:3000"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/unsplash")
public class UnsplashController {
    private final UnsplashService unsplashService;

    @GetMapping(value = "/search/{query}", produces = {"application/json"})
    public String search (@PathVariable String query) {
      return unsplashService.search(query);
    }
}
