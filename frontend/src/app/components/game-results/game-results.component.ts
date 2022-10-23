import { Component, Input } from '@angular/core';
import { GameResult } from 'src/app/models/game-result';
import { shapeMap } from 'src/app/models/shapes';

@Component({
  selector: 'app-game-results',
  templateUrl: './game-results.component.html',
  styleUrls: ['./game-results.component.scss']
})
export class GameResultsComponent {

  @Input() gameResult: GameResult | undefined;
  shapeMap = shapeMap;

  constructor() { }


}
