package com.experiment.reactive.common;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class InvalidImage {
    private String name;
    private String url;
    @Builder.Default
    private List<String> errors = new ArrayList<>();
}
