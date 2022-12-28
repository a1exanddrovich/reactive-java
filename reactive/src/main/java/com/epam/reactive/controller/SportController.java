package com.epam.reactive.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.reactive.domain.Sport;
import com.epam.reactive.service.SportService;
import com.epam.reactive.setup.Data;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/controller/sport")
public class SportController {

    @Autowired
    private SportService service;
    @Autowired
    private RestTemplate template;

    @GetMapping
    public Mono<Sport> getSport(@RequestParam String q) {
        return service.findSport(q);
    }

    @PostMapping("/{sportname}")
    public Mono<Sport> addSport(@PathVariable("sportname") String name) {
        return service.addSport(name);
    }

    @GetMapping("/all")
    public Flux<Sport> getAll() {
        return service.listAll();
    }

    @GetMapping("/load")
    public Mono<Void> load(@RequestParam("from") String url) {
        return Flux.fromStream(template
                        .getForEntity(url, Data.class)
                        .getBody()
                        .getData()
                        .stream()
                        .map(r -> r.getAttributes().getName())
                        .filter(Objects::nonNull)
                        .distinct())
                .flatMap(service::addSport)
                .then();

    }

}
