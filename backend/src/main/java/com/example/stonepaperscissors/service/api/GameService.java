package com.example.stonepaperscissors.service.api;

import com.example.stonepaperscissors.common.Shape;
import com.example.stonepaperscissors.service.model.GameResultTo;
import com.example.stonepaperscissors.service.model.GameStatistics;

public interface GameService {

    GameResultTo playGame(Shape playerHandShape);

    GameStatistics getStatistics();

    void clearStatistics();

}
