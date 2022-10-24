package com.example.stonepaperscissors.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.example.stonepaperscissors.TestUtils;
import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.service.impl.GameServiceImpl;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    GameServiceImpl gameService;

    @InjectMocks
    GameController gameController;

    @Test
    void playReturnsResult() {
        var gameResult = TestUtils.getGameResultTo(Shape.STONE, Shape.STONE, Result.DRAW);
        when(this.gameService.playGame(any())).thenReturn(gameResult);

        var response = this.gameController.play(Shape.SCISSORS);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Result.DRAW, response.getBody().getResult());
    }

    @Test
    void statisticsReturnsData() {
        var statistics = TestUtils.getStatistics();
        when(this.gameService.getStatistics()).thenReturn(statistics);

        var response = this.gameController.getGameStatistics();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5L, response.getBody().getPlayedGames());
    }

    @Test
    void statisticsThrowsError() {
        doThrow(new RuntimeException()).when(this.gameService).getStatistics();

        var response = this.gameController.getGameStatistics();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

}
