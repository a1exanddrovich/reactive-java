package com.epam.reactive.repository;

import reactor.core.publisher.Mono;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.reactive.domain.Sport;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, Integer> {

    Mono<Sport> findByName(String name);

}
