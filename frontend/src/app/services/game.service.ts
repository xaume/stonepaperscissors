import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { GameResult } from '../models/game-result';
import { GameStatistics } from '../models/game-statistics';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl: string;

  constructor(private http: HttpClient) { 
    this.baseUrl = environment.baseUrl
  }

  public play(playerShape: string) {
    return this.http.get<GameResult> (this.baseUrl + 'play/' + playerShape);
  }

  public getStatistics() {
    return this.http.get<GameStatistics> (this.baseUrl + 'statistics/');
  }

  public deleteStatistics() {
    return this.http.delete<void> (this.baseUrl + 'statistics/');
  }
}
