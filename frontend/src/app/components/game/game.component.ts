import { Component, OnInit } from '@angular/core';
import { GameResult } from 'src/app/models/game-result';
import { GameService } from 'src/app/services/game.service';

import { shapeMap, shapes } from '../../models/shapes';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  gameResult: GameResult | undefined;
  shapes = shapes;  

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
  }

  play (shapeSelected: string) {
    console.log(`Selected ${shapeSelected.toUpperCase()}`);
    
    this.gameService.play(shapeSelected.toUpperCase()).subscribe( data => {
      this.gameResult = data;
      console.log(this.gameResult);
    });
    
  }

}
