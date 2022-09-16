package com.vandidroid.digitalnomaddestinations.controller;

import com.vandidroid.digitalnomaddestinations.service.UnsplashService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
