package com.experiment.reactive.dto;

import com.experiment.reactive.common.ImageData;
import lombok.Data;

import java.util.List;

@Data
public class ImageRequest {
    private List<ImageData> images;
}
