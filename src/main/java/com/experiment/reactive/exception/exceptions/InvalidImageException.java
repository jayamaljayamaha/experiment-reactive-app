package com.experiment.reactive.exception.exceptions;

import com.experiment.reactive.common.InvalidImage;
import lombok.Getter;

import java.util.List;

@Getter
public class InvalidImageException extends RuntimeException{

    private InvalidImage invalidImage;

    public InvalidImageException(InvalidImage invalidImage){
        this.invalidImage = invalidImage;
    }
}
