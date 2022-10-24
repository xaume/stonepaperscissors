package com.example.stonepaperscissors.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.stonepaperscissors.TestUtils;
import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.common.Utils;
import com.example.stonepaperscissors.dataaccess.repository.GameResultRepository;
import com.example.stonepaperscissors.service.impl.GameServiceImpl;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    GameResultRepository repository;

    @InjectMocks
    GameServiceImpl gameService;

    @Test
    void playerDraws() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.STONE, Shape.STONE, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.STONE);
            var gameResultTo = gameService.playGame(Shape.STONE);

            assertNotNull(gameResultTo);
            assertEquals(Result.DRAW, gameResultTo.getResult());
            assertEquals(Shape.STONE, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerWinsWithStone() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.STONE, Shape.SCISSORS, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.SCISSORS);
            var gameResultTo = gameService.playGame(Shape.STONE);

            assertNotNull(gameResultTo);
            assertEquals(Result.WIN, gameResultTo.getResult());
            assertEquals(Shape.SCISSORS, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerLossesWithStone() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.STONE, Shape.PAPER, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.PAPER);
            var gameResultTo = gameService.playGame(Shape.STONE);

            assertNotNull(gameResultTo);
            assertEquals(Result.LOSS, gameResultTo.getResult());
            assertEquals(Shape.PAPER, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerWinsWithPaper() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.PAPER, Shape.STONE, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.STONE);
            var gameResultTo = gameService.playGame(Shape.PAPER);

            assertNotNull(gameResultTo);
            assertEquals(Result.WIN, gameResultTo.getResult());
            assertEquals(Shape.STONE, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerLossesWithPaper() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.PAPER, Shape.SCISSORS, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.SCISSORS);
            var gameResultTo = gameService.playGame(Shape.PAPER);

            assertNotNull(gameResultTo);
            assertEquals(Result.LOSS, gameResultTo.getResult());
            assertEquals(Shape.SCISSORS, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerWinsWithScissors() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.SCISSORS, Shape.PAPER, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.PAPER);
            var gameResultTo = gameService.playGame(Shape.SCISSORS);

            assertNotNull(gameResultTo);
            assertEquals(Result.WIN, gameResultTo.getResult());
            assertEquals(Shape.PAPER, gameResultTo.getComputerShape());
        }
    }

    @Test
    void playerLossesWithScissors() {
        var gameResult = TestUtils.getGameResultEntity(1L, Shape.SCISSORS, Shape.STONE, Result.DRAW);
        when(repository.save(any())).thenReturn(gameResult);

        try (var utils = mockStatic(Utils.class)) {
            utils.when(Utils::randomShape).thenReturn(Shape.STONE);
            var gameResultTo = gameService.playGame(Shape.SCISSORS);

            assertNotNull(gameResultTo);
            assertEquals(Result.LOSS, gameResultTo.getResult());
            assertEquals(Shape.STONE, gameResultTo.getComputerShape());
        }
    }

    @Test
    void getStatistics() {
        var gameResults = TestUtils.getGameResults();
        when(repository.findAll()).thenReturn(gameResults);
        when(repository.getPlayerGamesByShape()).thenReturn(TestUtils.getPlayerShapes(gameResults));
        when(repository.getComputerGamesByShape()).thenReturn(TestUtils.getComputerShapes(gameResults));

        var statistics = gameService.getStatistics();

        assertNotNull(statistics);
        assertEquals(5L, statistics.getPlayedGames());
        assertEquals(2L, statistics.getTotalWins());
        assertEquals(2L, statistics.getTotalLoss());
        assertEquals(1L, statistics.getTotalDraws());
        assertNotNull(statistics.getPlayerShapes());
        assertNotNull(statistics.getComputerShapes());
        assertAll("Lists should contain only three items (one per shape)",
                () -> assertEquals(3L, statistics.getPlayerShapes().size()),
                () -> assertEquals(3L, statistics.getComputerShapes().size()));
        assertAll("Player shapes match the expected count",
                () -> assertEquals(3L, statistics.getPlayerShapes()
                        .stream()
                        .filter(s -> Shape.STONE.equals(s.getShape()))
                        .findFirst().get().getCount()),
                () -> assertEquals(1L, statistics.getPlayerShapes()
                        .stream()
                        .filter(s -> Shape.SCISSORS.equals(s.getShape()))
                        .findFirst().get().getCount()),
                () -> assertEquals(1L, statistics.getPlayerShapes()
                        .stream()
                        .filter(s -> Shape.PAPER.equals(s.getShape()))
                        .findFirst().get().getCount()));
        assertAll("Computer shapes match the expected count",
                () -> assertEquals(3L, statistics.getComputerShapes()
                        .stream()
                        .filter(s -> Shape.STONE.equals(s.getShape()))
                        .findFirst().get().getCount()),
                () -> assertEquals(1L, statistics.getComputerShapes()
                        .stream()
                        .filter(s -> Shape.SCISSORS.equals(s.getShape()))
                        .findFirst().get().getCount()),
                () -> assertEquals(1L, statistics.getComputerShapes()
                        .stream()
                        .filter(s -> Shape.PAPER.equals(s.getShape()))
                        .findFirst().get().getCount()));

    }

    @Test
    void clearStatistics() {
        doNothing().when(repository).deleteAll();
        gameService.clearStatistics();
        verify(repository, times(1)).deleteAll();
    }

}
