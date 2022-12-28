package com.epam.reactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.epam.reactive.handler.SportHandler;

@Configuration
public class SportRouter {

    public static final String FIND_SPORT_ROUTE = "/api/v1/sport";
    public static final String ADD_SPORT_ROUTE = "/api/v1/sport/{sportname}";

    @Bean
    public RouterFunction<ServerResponse> route(SportHandler handler) {
        RequestPredicate getSportRoute = RequestPredicates
                .GET(FIND_SPORT_ROUTE);

        RequestPredicate postSportRoute = RequestPredicates
                .POST(ADD_SPORT_ROUTE);

        return RouterFunctions
                .route(getSportRoute, handler::findSport)
                .andRoute(postSportRoute, handler::addSport);
    }

}
