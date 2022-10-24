package com.example.stonepaperscissors.service.model;

import java.util.List;

import com.example.stonepaperscissors.common.ShapeCount;

import lombok.Data;

@Data
public class GameStatistics {

    private long playedGames;
    private long totalWins;
    private long totalDraws;
    private long totalLoss;
    private List<ShapeCount> playerShapes;
    private List<ShapeCount> computerShapes;

}
