import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgxChartsModule } from '@swimlane/ngx-charts';

import { AppComponent } from './app.component';
import { GameComponent } from './components/game/game.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { GameResultsComponent } from './components/game-results/game-results.component';

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    NavBarComponent,
    StatisticsComponent,
    GameResultsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgxChartsModule,
    RouterModule.forRoot([
      { path: '', component: GameComponent },
      { path: 'statistics', component: StatisticsComponent }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
