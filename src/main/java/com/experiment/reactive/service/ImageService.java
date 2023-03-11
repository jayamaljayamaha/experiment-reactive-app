package com.experiment.reactive.service;

import com.experiment.reactive.common.ImageData;
import com.experiment.reactive.common.ReturnImage;
import com.experiment.reactive.dto.ImageRequest;
import com.experiment.reactive.dto.ImageResponse;
import reactor.core.publisher.Flux;

public interface ImageService {

    Flux<ImageResponse> saveImages(Flux<ImageData> imageData);
}
