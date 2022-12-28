package com.epam.reactive.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
public class Sport {

    @Id
    private int id;
    private String name;

    public Sport(String name) {
        this.name = name;
    }

}
