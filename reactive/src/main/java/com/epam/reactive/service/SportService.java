package com.epam.reactive.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reactive.backpressure.CustomBackpressureSubscriber;
import com.epam.reactive.domain.Sport;
import com.epam.reactive.exception.EntityAlreadyExistException;
import com.epam.reactive.repository.SportRepository;

@Service
public class SportService {

    public static final String ERROR_MESSAGE = "Such sport has been already added";

    @Autowired
    private SportRepository repository;

    public Mono<Sport> addSport(String sportName) {
        return repository
                .findByName(sportName)
                .hasElement()
                .flatMap(isNamePresent -> isNamePresent
                        ? Mono.error(new EntityAlreadyExistException(ERROR_MESSAGE))
                        : repository.save(new Sport(sportName)));
    }

    public Mono<Sport> findSport(String name) {
        return repository.findByName(name);
    }

    public Flux<Sport> listAll() {
        Flux<Sport> sportFlux = repository.findAll();
        sportFlux.log().subscribe(new CustomBackpressureSubscriber());
        return sportFlux;
    }

}
