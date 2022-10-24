package com.example.stonepaperscissors.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.common.Utils;
import com.example.stonepaperscissors.dataaccess.entity.GameResult;
import com.example.stonepaperscissors.dataaccess.repository.GameResultRepository;
import com.example.stonepaperscissors.service.api.GameService;
import com.example.stonepaperscissors.service.model.GameResultTo;
import com.example.stonepaperscissors.service.model.GameStatistics;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private GameResultRepository gameResultRepository;

    @Autowired
    public GameServiceImpl(GameResultRepository gameResultRepository) {
        this.gameResultRepository = gameResultRepository;
    }

    @Override
    public GameResultTo playGame(Shape playerShape) {
        GameResultTo gameResult = new GameResultTo();

        Shape computerShape = Utils.randomShape();
        Result result = compareHands(playerShape, computerShape);

        gameResult.setPlayerShape(playerShape);
        gameResult.setComputerShape(computerShape);
        gameResult.setResult(result);
        saveGameResult(gameResult);

        log.info("[Player] {} vs {} [Computer] - Result: {}", playerShape, computerShape, result);

        return gameResult;
    }

    @Override
    public GameStatistics getStatistics() {
        GameStatistics gameStatistics = new GameStatistics();

        List<GameResult> results = this.gameResultRepository.findAll();

        long totalWins = results.stream().filter(r -> Result.WIN.equals(r.getResult())).count();
        long totalDraws = results.stream().filter(r -> Result.DRAW.equals(r.getResult())).count();
        long totalLoss = results.stream().filter(r -> Result.LOSS.equals(r.getResult())).count();

        gameStatistics.setPlayedGames(results.size());
        gameStatistics.setTotalWins(totalWins);
        gameStatistics.setTotalDraws(totalDraws);
        gameStatistics.setTotalLoss(totalLoss);
        gameStatistics.setPlayerShapes(this.gameResultRepository.getPlayerGamesByShape());
        gameStatistics.setComputerShapes(this.gameResultRepository.getComputerGamesByShape());

        return gameStatistics;
    }

    @Override
    public void clearStatistics() {
        this.gameResultRepository.deleteAll();
    }

    private Result compareHands(Shape playerShape, Shape computerShape) {
        if (playerShape.equals(computerShape)) {
            return Result.DRAW;
        }
        if (Shape.STONE.equals(playerShape)) {
            if (Shape.SCISSORS.equals(computerShape)) {
                return Result.WIN;
            }
            else {
                return Result.LOSS;
            }
        }
        else if (Shape.SCISSORS.equals(playerShape)) {
            if (Shape.PAPER.equals(computerShape)) {
                return Result.WIN;
            }
            else {
                return Result.LOSS;
            }
        }
        else if (Shape.STONE.equals(computerShape)) {
            return Result.WIN;
        }
        else {
            return Result.LOSS;
        }
    }

    private GameResult saveGameResult(GameResultTo gameResultTo) {
        GameResult gameResult = new GameResult();
        gameResult.setPlayerShape(gameResultTo.getPlayerShape());
        gameResult.setComputerShape(gameResultTo.getComputerShape());
        gameResult.setResult(gameResultTo.getResult());
        return this.gameResultRepository.save(gameResult);
    }

}
