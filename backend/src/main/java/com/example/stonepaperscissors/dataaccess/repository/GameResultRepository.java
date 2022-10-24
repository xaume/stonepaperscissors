package com.example.stonepaperscissors.dataaccess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.stonepaperscissors.common.ShapeCount;
import com.example.stonepaperscissors.dataaccess.entity.GameResult;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {

    @Query(value = "SELECT playerShape as shape, count(id) as count FROM GameResult GROUP BY playerShape")
    List<ShapeCount> getPlayerGamesByShape();

    @Query(value = "SELECT computerShape as shape, count(id) as count FROM GameResult GROUP BY computerShape")
    List<ShapeCount> getComputerGamesByShape();
}
