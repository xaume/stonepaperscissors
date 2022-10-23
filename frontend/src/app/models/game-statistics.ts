export interface ShapeCount {
  shape: string
  count: number
}

export interface GameStatistics {
  playedGames: number
  totalWins: number
  totalDraws: number
  totalLoss: number
  playerShapes: ShapeCount[]
  computerShapes: ShapeCount[]
}
