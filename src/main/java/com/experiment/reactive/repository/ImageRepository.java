package com.experiment.reactive.repository;

import com.experiment.reactive.entity.Image;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ImageRepository extends R2dbcRepository<Image, Long> {
}
