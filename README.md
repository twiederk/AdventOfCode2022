# Advent Of Code 2022

### Overview of the puzzles

|  Day | Title                   | Part 1 | Part 2 | Notes                                                                                                                            |
|-----:|-------------------------|--------|--------|----------------------------------------------------------------------------------------------------------------------------------|
|  [1] | Calorie Counting        | solved | solved | Blocks of Ints separated by new line                                                                                             |
|  [2] | Rock Paper Scissors     | solved | solved | Should use map with all combinations instead of my current solution                                                              |
|  [3] | Rucksack Reorganization | solved | solved | Scan single line string                                                                                                          |
|  [4] | Camp Cleanup            | solved | solved | Overlapping ranges                                                                                                               |
|  [5] | Supply Stacks           | solved | solved | Reminds me of Tower of Hanoi                                                                                                     |
|  [6] | Tuning Trouble          | solved | solved | Scan single line string                                                                                                          |
|  [7] | No Space Left On Device | solved | solved | Scanning a directory tree                                                                                                        |
|  [8] | Treetop Tree House      | solved | solved | First 2D puzzle.                                                                                                                 |
|  [9] | Rope Bridge             | solved | solved | Got problem in part 2, because I didn't realized that other knots can be two steps away now. Found solution by reading [reddit]. |
| [10] | Cathode-Ray Tube        | solved | solved | Great puzzle! This time the solution is no number it is text displayed on a device!                                              |
| [11] | Monkey in the Middle    | solved | solved | Part was easy. Couldn't solve part 2 with out [video][Day11-Video] of [Sebastian Aigner][sebi] and [Olaf Gottschalk][olaf].      |
| [12] | Hill Climbing Algorithm | solved | solved | Took a lot of time implementing my first aStart (A*) search in Advent of Code                                                    |
| [13] | Distress Signal         | solved | solved | Needed to tries to solve it. Hard part was to simplify the input, but the it worked.                                             |
| [14] | Regolith Reservoir      | solved | solved | Falling snow/sand. Very nice puzzle. Solved part 2 by just expanding my grid (brute force?)                                      |
| [15] | Beacon Exclusion Zone   | solved | solved | Part 1 was easy, but failed in part 2 because it created all points. Replaced points with ranges this worked!                    |
| [16] | Proboscidea Volcanium   | solved | solved | No idea how to solve this. Used solution of [Christoph Kainz][Day16-CK]                                                          |
| [17] | PyroclasticFlow         | solved | OPEN   | Tetris! Solved part 2 on my own, but ran in many easy avoidable bug. Part 2 still open.                                          |
| [18] | Boiling Boulders        | solved | solved | First 3D puzzle (lava cube). Solved part 1 on my own. Solved part 2 based on [solution from Todd Ginsberg][Day18-TG]             |

[1]: src/main/kotlin/Day01CalorieCounting.kt
[2]: src/main/kotlin/Day02RockPaperScissors.kt
[3]: src/main/kotlin/Day03RucksackReorganization.kt
[4]: src/main/kotlin/Day04CampCleanup.kt
[5]: src/main/kotlin/Day05SupplyStacks.kt
[6]: src/main/kotlin/Day06TuningTrouble.kt
[7]: src/main/kotlin/Day07NoSpaceLeftOnDevice.kt
[8]: src/main/kotlin/Day08TreetopTreeHouse.kt
[9]: src/main/kotlin/Day09RopeBridge.kt
[10]: src/main/kotlin/Day10CathodeRayTube.kt
[11]: src/main/kotlin/Day11MonkeyInTheMiddle.kt
[12]: src/main/kotlin/Day12HillClimbingAlgorithm.kt
[13]: src/main/kotlin/Day13DistressSignal.kt
[14]: src/main/kotlin/Day14RegolithReservoir.kt
[15]: src/main/kotlin/Day15BeaconExclusionZone.kt
[16]: src/main/kotlin/Day16ProboscideaVolcanium.kt
[17]: src/main/kotlin/Day17PyroclasticFlow.kt
[18]: src/main/kotlin/Day18BoilingBoulders.kt


[Day11-Video]: https://youtu.be/1eBSyPe_9j0?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J
[Day16-CK]: https://github.com/ckainz11/AdventOfCode2022/blob/main/src/main/kotlin/days/day16/Day16.kt
[Day18-TG]: https://todd.ginsberg.com/post/advent-of-code/2022/day18/

[reddit]: https://www.reddit.com/r/adventofcode/
[sebi]: https://github.com/SebastianAigner
[olaf]: https://github.com/Zordid
