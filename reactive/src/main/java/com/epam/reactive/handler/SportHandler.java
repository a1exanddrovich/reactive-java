package com.epam.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.epam.reactive.domain.Sport;
import com.epam.reactive.service.SportService;

import reactor.core.publisher.Mono;

@Component
public class SportHandler {

    public static final String SPORT_NAME_PARAMETER = "q";
    public static final String SPORT_NAME_PATH_VARIABLE= "sportname";

    @Autowired
    private SportService service;

    public Mono<ServerResponse> findSport(ServerRequest request) {
        return request
                .queryParam(SPORT_NAME_PARAMETER)
                .map(sportName -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.findSport(sportName), Sport.class))
                .orElseGet(() -> ServerResponse
                        .status(400)
                        .build());
    }

    public Mono<ServerResponse> addSport(ServerRequest request) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.addSport(request.pathVariable(SPORT_NAME_PATH_VARIABLE)), Sport.class);
    }

}
