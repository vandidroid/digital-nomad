package com.vandidroid.digitalnomaddestinations.service;

import com.vandidroid.digitalnomaddestinations.model.dto.UnsplashCommand;
import com.vandidroid.digitalnomaddestinations.model.entity.Location;
import com.vandidroid.digitalnomaddestinations.model.entity.Unsplash;
import com.vandidroid.digitalnomaddestinations.repository.LocationRepository;
import com.vandidroid.digitalnomaddestinations.repository.UnsplashRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnsplashService {
    private final UnsplashRepository unsplashRepository;
    private final LocationRepository locationRepository;

    private final RestTemplate restTemplate;
    private final Environment environment;

    public String search (String query) {
        String url = "https://api.unsplash.com/search/photos?page=1&per_page=1&orientation=landscape&query=" + query;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + environment.getProperty("UNSPLASH_TOKEN"));

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

        return response.getBody();
    }

    public List<Unsplash> findAll() {
        return unsplashRepository.findAll();
    }

    public Unsplash add(UnsplashCommand unsplashCommand) {
        Unsplash unsplash = new Unsplash();
        unsplash.setData(unsplashCommand.getData());
        Location location = locationRepository.findById(unsplashCommand.getLocationId()).orElseThrow(RuntimeException::new);
        unsplash.setLocation(location);
        return unsplashRepository.save(unsplash);
    }

    public Unsplash findById(Long id) {
        return unsplashRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Long id) {
        unsplashRepository.deleteById(id);
    }

    public Unsplash update(Long id, UnsplashCommand unsplashCommand) {
        Unsplash unsplash = unsplashRepository.findById(id).orElseThrow(RuntimeException::new);
        unsplash.setId(id);
        unsplash.setData(unsplashCommand.getData());
        Location location = locationRepository.findById(unsplashCommand.getLocationId()).orElseThrow(RuntimeException::new);
        unsplash.setLocation(location);
        return unsplashRepository.save(unsplash);
    }

    public long count() {
        return unsplashRepository.count();
    }
}
