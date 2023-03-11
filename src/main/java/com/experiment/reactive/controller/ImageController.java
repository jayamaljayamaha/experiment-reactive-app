package com.experiment.reactive.controller;

import com.experiment.reactive.common.ImageData;
import com.experiment.reactive.common.ReturnImage;
import com.experiment.reactive.dto.ImageRequest;
import com.experiment.reactive.dto.ImageResponse;
import com.experiment.reactive.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/reactive/image")
public class ImageController {

    @Autowired
    @Qualifier("imageServiceReactive")
    private ImageService imageService;

    @PostMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ImageResponse> saveImages(@RequestBody Flux<ImageData> imageData){
        return imageService.saveImages(imageData);
    }
}
