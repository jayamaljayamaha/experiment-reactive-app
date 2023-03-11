package com.experiment.reactive.dto;

import com.experiment.reactive.common.InvalidImage;
import com.experiment.reactive.common.ReturnImage;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class ImageResponse implements Serializable {
    private boolean isSuccess;
    private ReturnImage image;
    private InvalidImage invalidImage;

}
