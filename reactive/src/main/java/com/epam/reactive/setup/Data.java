package com.epam.reactive.setup;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
@Getter
public class Data {

    @JsonProperty("data")
    private List<ResponseDto> data;

}
