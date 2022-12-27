# Advent Of Code 2022

## Overview of the puzzles

|  Day | Title                    | Part 1 | Part 2 | Notes                                                                                                                            |
|-----:|--------------------------|--------|--------|----------------------------------------------------------------------------------------------------------------------------------|
|  [1] | Calorie Counting         | solved | solved | Blocks of Ints separated by new line                                                                                             |
|  [2] | Rock Paper Scissors      | solved | solved | Should use map with all combinations instead of my current solution                                                              |
|  [3] | Rucksack Reorganization  | solved | solved | Scan single line string                                                                                                          |
|  [4] | Camp Cleanup             | solved | solved | Overlapping ranges                                                                                                               |
|  [5] | Supply Stacks            | solved | solved | Towers of Hanoi                                                                                                                  |
|  [6] | Tuning Trouble           | solved | solved | Scan single line string                                                                                                          |
|  [7] | No Space Left On Device  | solved | solved | Scanning a directory tree                                                                                                        |
|  [8] | Treetop Tree House       | solved | solved | First 2D puzzle.                                                                                                                 |
|  [9] | Rope Bridge              | solved | solved | Got problem in part 2, because I didn't realized that other knots can be two steps away now. Found solution by reading [reddit]. |
| [10] | Cathode-Ray Tube         | solved | solved | Great puzzle! This time the solution is no number it is text displayed on a device!                                              |
| [11] | Monkey in the Middle     | solved | solved | Part 1 was easy. Couldn't solve part 2 with out [video][Day11-Video] of [Sebastian Aigner][sebi] and [Olaf Gottschalk][olaf].    |
| [12] | Hill Climbing Algorithm  | solved | solved | Took a lot of time implementing my first aStart (A*) search in Advent of Code.                                                   |
| [13] | Distress Signal          | solved | solved | Needed two tries to solve it. Hard part was to simplify the input, but after solving this it worked.                             |
| [14] | Regolith Reservoir       | solved | solved | Falling snow/sand. Very nice puzzle. Solved part 2 by just expanding my grid (brute force?)                                      |
| [15] | Beacon Exclusion Zone    | solved | solved | Part 1 was easy, but failed in part 2 because it created all points. Replaced points with ranges this worked!                    |
| [16] | Proboscidea Volcanium    | solved | solved | No idea how to solve this. Used solution of [Christoph Kainz][Day16-CK]                                                          |
| [17] | PyroclasticFlow          | solved | OPEN   | Tetris! Solved part 2 on my own, but ran in many easy avoidable bugs. Part 2 still open.                                         |
| [18] | Boiling Boulders         | solved | solved | First 3D puzzle (lava cube). Solved part 1 on my own. Solved part 2 based on [solution from Todd Ginsberg][Day18-TG]             |
| [19] | Not Enough Minerals      | solved | OPEN   | RTS. Solved part 1 based on [solution from Todd Ginsberg][Day19-TG]                                                              |
| [20] | Grove Positioning System | OPEN   | OPEN   | not started                                                                                                                      |
| [21] | Monkey Math              | solved | OPEN   | Solution of part 1 is unusable for solving part 2.                                                                               |
| [22] |                          | OPEN   | OPEN   | not started                                                                                                                      |
| [23] |                          | OPEN   | OPEN   | not started                                                                                                                      |
| [24] |                          | OPEN   | OPEN   | not started                                                                                                                      |
| [25] |                          | OPEN   | OPEN   | not started                                                                                                                      |

## Resources

* [Todd Ginsberg blog][todd]
* [Advent of code Jetbrains Playlist][jetbrains]
* [reddit][reddit]


## To Do
* Day 12:
  * Implement BFS and aStar as visible solution
  * Check how to fulfill licence of PixelGameEngine
  * Add visible solution form PGE Tutorial repo
  * solve with breadth-first search (BFS)
* Day 18: replace foreach with while (queue.isNotEmpty()) because it is more readable
* Day 10, 12 and 14 have render methods => check them

[1]: src/main/kotlin/Day01.kt
[2]: src/main/kotlin/Day02.kt
[3]: src/main/kotlin/Day03.kt
[4]: src/main/kotlin/Day04.kt
[5]: src/main/kotlin/Day05.kt
[6]: src/main/kotlin/Day06.kt
[7]: src/main/kotlin/Day07.kt
[8]: src/main/kotlin/Day08.kt
[9]: src/main/kotlin/Day09.kt
[10]: src/main/kotlin/Day10.kt
[11]: src/main/kotlin/Day11.kt
[12]: src/main/kotlin/Day12.kt
[13]: src/main/kotlin/Day13.kt
[14]: src/main/kotlin/Day14.kt
[15]: src/main/kotlin/Day15.kt
[16]: src/main/kotlin/Day16.kt
[17]: src/main/kotlin/Day17.kt
[18]: src/main/kotlin/Day18.kt
[19]: src/main/kotlin/Day19.kt
[20]: src/main/kotlin/Day20.kt
[21]: src/main/kotlin/Day21.kt
[22]: src/main/kotlin/Day22.kt
[23]: src/main/kotlin/Day23.kt
[24]: src/main/kotlin/Day24.kt
[25]: src/main/kotlin/Day25.kt


[Day11-Video]: https://youtu.be/1eBSyPe_9j0?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J
[Day16-CK]: https://github.com/ckainz11/AdventOfCode2022/blob/main/src/main/kotlin/days/day16/Day16.kt
[Day18-TG]: https://todd.ginsberg.com/post/advent-of-code/2022/day18/
[Day19-TG]: https://todd.ginsberg.com/post/advent-of-code/2022/day19/

[reddit]: https://www.reddit.com/r/adventofcode/
[sebi]: https://github.com/SebastianAigner
[olaf]: https://github.com/Zordid
[todd]: https://todd.ginsberg.com/post/advent-of-code/2022/
[jetbrains]: https://www.youtube.com/playlist?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J