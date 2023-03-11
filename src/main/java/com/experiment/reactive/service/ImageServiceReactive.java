package com.experiment.reactive.service;

import com.experiment.reactive.common.Device;
import com.experiment.reactive.common.ImageData;
import com.experiment.reactive.common.InvalidImage;
import com.experiment.reactive.common.ReturnImage;
import com.experiment.reactive.dto.ImageResponse;
import com.experiment.reactive.entity.Image;
import com.experiment.reactive.exception.exceptions.InvalidImageException;
import com.experiment.reactive.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

@Service
public class ImageServiceReactive implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Validator validator;

    @Override
    public Flux<ImageResponse> saveImages(Flux<ImageData> imageData) {
        return imageData.map(image -> Pair.of(image, validator.validate(image)))
                .flatMap(pair -> pair.getSecond().isEmpty() ? saveInDb(pair.getFirst()) : handleInvalidImages(pair.getFirst(), pair.getSecond()));
    }

    private Mono<ImageResponse> saveInDb(ImageData imageData) {
        return imageRepository.save(this.createImageEntity(imageData))
                .map(savedImage -> ImageResponse.builder().isSuccess(true).image(ReturnImage.builder().id(savedImage.getId())
                        .url(savedImage.getUrl()).name(savedImage.getName()).build()).build());
    }

    private Mono<ImageResponse> handleInvalidImages(ImageData imageData, Set<ConstraintViolation<ImageData>> violations) {
        return Mono.just(imageData).map(image -> ImageResponse.builder().isSuccess(false).invalidImage(InvalidImage.builder()
                .errors(violations.stream().map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage())
                        .toList()).name(imageData.getName()).url(imageData.getUrl()).build()).build());
    }

    Function<InvalidImageException, Flux<ImageResponse>> exceptionResolver = exception ->
            Flux.just(ImageResponse.builder().isSuccess(false).invalidImage(exception.getInvalidImage()).build());

    private ImageData validateImage(Long index, ImageData imageData) {
        Set<ConstraintViolation<ImageData>> violations = validator.validate(imageData);
        if (!violations.isEmpty()) {
            throw new InvalidImageException(InvalidImage.builder().url(imageData.getUrl())
                    .name(imageData.getName()).errors(violations.stream()
                            .map(violation -> violation.getPropertyPath().toString() + ": " + violation.getMessage()).toList()).build());
        }
        return imageData;
    }

    private Image createImageEntity(ImageData imageData) {
        return Image.builder()
                .id(UUID.randomUUID().getLeastSignificantBits())
                .name(imageData.getName())
                .url(imageData.getUrl())
                .width(imageData.getWidth())
                .height(imageData.getHeight())
                .numberOfPixels(imageData.getNumberOfPixels())
                .format(imageData.getFormat())
                .createdDate(imageData.getCreatedDate())
                .lastModifiedDate(imageData.getLastModifiedDate())
                .size(imageData.getSize())
                .capturedBy(imageData.getCapturedBy())
                .device(Device.valueOf(imageData.getDevice()))
                .build().setAsNew();
    }

}
