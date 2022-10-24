package com.example.stonepaperscissors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.service.api.GameService;
import com.example.stonepaperscissors.service.model.GameResultTo;
import com.example.stonepaperscissors.service.model.GameStatistics;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Stone Paper Scissors Game")
@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "${cors.crossorigins}")
@Slf4j
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Plays a game")
    @GetMapping("/play/{playerShape}")
    public ResponseEntity<GameResultTo> play(@PathVariable Shape playerShape) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noCache())
                .body(this.gameService.playGame(playerShape));
    }

    @Operation(summary = "Gets game statistics")
    @GetMapping("/statistics")
    public ResponseEntity<GameStatistics> getGameStatistics() {
        try {
            return ResponseEntity.ok(this.gameService.getStatistics());
        } catch (Exception e) {
            log.error("An error happened trying to recover statistics", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @Operation(summary = "Delete game statistics")
    @DeleteMapping("/statistics")
    public ResponseEntity<Object> deleteGameStatistics() {
        try {
            this.gameService.clearStatistics();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("An error happened trying to remove statistics", e);
            return ResponseEntity.internalServerError().build();
        }
    }

}
