package com.example.stonepaperscissors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.common.ShapeCount;
import com.example.stonepaperscissors.dataaccess.entity.GameResult;
import com.example.stonepaperscissors.service.model.GameResultTo;
import com.example.stonepaperscissors.service.model.GameStatistics;

public class TestUtils {

    public static GameResult getGameResultEntity(Long id, Shape playerShape, Shape computerShape, Result result) {
        var gameResult = new GameResult();

        gameResult.setId(id);
        gameResult.setPlayerShape(playerShape);
        gameResult.setComputerShape(computerShape);
        gameResult.setResult(result);
        return gameResult;
    }

    public static GameResultTo getGameResultTo(Shape playerShape, Shape computerShape, Result result) {
        var gameResult = new GameResultTo();

        gameResult.setPlayerShape(playerShape);
        gameResult.setComputerShape(computerShape);
        gameResult.setResult(result);
        return gameResult;
    }

    public static List<GameResult> getGameResults() {
        var gameResults = new ArrayList<GameResult>();

        gameResults.add(getGameResultEntity(1L, Shape.STONE, Shape.SCISSORS, Result.WIN));
        gameResults.add(getGameResultEntity(2L, Shape.STONE, Shape.STONE, Result.DRAW));
        gameResults.add(getGameResultEntity(3L, Shape.SCISSORS, Shape.STONE, Result.LOSS));
        gameResults.add(getGameResultEntity(4L, Shape.PAPER, Shape.STONE, Result.WIN));
        gameResults.add(getGameResultEntity(5L, Shape.STONE, Shape.PAPER, Result.LOSS));

        return gameResults;
    }

    public static List<ShapeCount> getPlayerShapes(List<GameResult> gameResults) {
        var shapes = new ArrayList<ShapeCount>();

        var factory = new SpelAwareProxyProjectionFactory();

        for (var shape : Shape.values()) {
            var shapeCount = factory.createProjection(ShapeCount.class);
            var count = gameResults.stream().filter(result -> shape.equals(result.getPlayerShape())).count();
            shapeCount.setShape(shape);
            shapeCount.setCount(count);
            shapes.add(shapeCount);
        }

        return shapes;
    }

    public static List<ShapeCount> getComputerShapes(List<GameResult> gameResults) {
        var shapes = new ArrayList<ShapeCount>();

        var factory = new SpelAwareProxyProjectionFactory();

        for (var shape : Shape.values()) {
            var shapeCount = factory.createProjection(ShapeCount.class);
            var count = gameResults.stream().filter(result -> shape.equals(result.getComputerShape())).count();
            shapeCount.setShape(shape);
            shapeCount.setCount(count);
            shapes.add(shapeCount);
        }

        return shapes;
    }

    public static GameStatistics getStatistics() {
        var gameStatistics = new GameStatistics();
        gameStatistics.setPlayedGames(5);
        gameStatistics.setTotalWins(2);
        gameStatistics.setTotalDraws(1);
        gameStatistics.setTotalLoss(2);
        gameStatistics.setPlayerShapes(getPlayerShapes(getGameResults()));
        gameStatistics.setComputerShapes(getComputerShapes(getGameResults()));
        return gameStatistics;
    }

}
