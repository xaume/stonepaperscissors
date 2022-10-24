import { Component, OnInit } from '@angular/core';
import { GameStatistics, ShapeCount } from 'src/app/models/game-statistics';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.scss']
})
export class StatisticsComponent implements OnInit {
  
  statistics: GameStatistics | undefined;

  playerData: {name: string, value: number}[] = [];
  computerData: {name: string, value: number}[] = [];
  resultsData: {name: string, value: number}[] = [];

  // Graph options
  showLabels: boolean = true;
  scheme: string = "cool";
  
  
  constructor(private gameService: GameService) { 
    this.gameService.getStatistics().subscribe(data => {
      this.statistics = data;

        for (let shapeCount of this.statistics.playerShapes) {
          this.playerData.push({name: shapeCount.shape, value: shapeCount.count});
        }
        for (let shapeCount of this.statistics.computerShapes) {
          this.computerData.push({name: shapeCount.shape, value: shapeCount.count});
        }
        this.resultsData.push({name: "Win", value:this.statistics.totalWins });
        this.resultsData.push({name: "Lost", value:this.statistics.totalLoss });
        this.resultsData.push({name: "Draw", value:this.statistics.totalDraws });
    });
  }
  
  ngOnInit(): void {

  }
  
}
