package com.example.stonepaperscissors.service.model;

import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class GameResultTo {

    @Schema(description = "Player's shape")
    private Shape playerShape;

    @Schema(description = "Computer's shape")
    private Shape computerShape;

    @Schema(description = "Result of the game")
    private Result result;

}
