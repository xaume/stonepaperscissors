export interface Shape {
  name: string
  icon: string
}

export const shapes = [
  {
    name: "Stone",
    icon: "hand-fist"
  },
  {
    name: "Paper",
    icon: "hand"
  },
  {
    name: "Scissors",
    icon: "hand-scissors"
  },
]

export const shapeMap = new Map<string, Shape>([
  ["STONE", shapes[0]],
  ["PAPER", shapes[1]],
  ["SCISSORS", shapes[2]]
])