package com.example.stonepaperscissors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.stonepaperscissors.controller.GameController;
import com.example.stonepaperscissors.service.api.GameService;

@SpringBootTest
class StonePaperScissorsApplicationTests {

    @Autowired
    private GameController gameController;

    @Autowired
    private GameService gameService;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(gameController);
        Assertions.assertNotNull(gameService);
    }

}
