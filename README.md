# Advent Of Code 2022

## Overview of the puzzles

|  Day | Title                      | Part 1  | Part 2  | Notes                                                                                                                            |
|-----:|----------------------------|:-------:|:-------:|----------------------------------------------------------------------------------------------------------------------------------|
|  [1] | [Calorie Counting]         | &#9733; | &#9733; | Blocks of Ints separated by new line                                                                                             |
|  [2] | [Rock Paper Scissors]      | &#9733; | &#9733; | Should use map with all combinations instead of my current solution                                                              |
|  [3] | [Rucksack Reorganization]  | &#9733; | &#9733; | Scan single line string                                                                                                          |
|  [4] | [Camp Cleanup]             | &#9733; | &#9733; | Overlapping ranges                                                                                                               |
|  [5] | [Supply Stacks]            | &#9733; | &#9733; | Towers of Hanoi                                                                                                                  |
|  [6] | [Tuning Trouble]           | &#9733; | &#9733; | Scan single line string                                                                                                          |
|  [7] | [No Space Left On Device]  | &#9733; | &#9733; | Scanning a directory tree                                                                                                        |
|  [8] | [Treetop Tree House]       | &#9733; | &#9733; | First 2D puzzle.                                                                                                                 |
|  [9] | [Rope Bridge]              | &#9733; | &#9733; | Got problem in part 2, because I didn't realized that other knots can be two steps away now. Found solution by reading [reddit]. |
| [10] | [Cathode-Ray Tube]         | &#9733; | &#9733; | Great puzzle! This time the solution is no number it is text displayed on a device!                                              |
| [11] | [Monkey in the Middle]     | &#9733; | &#9733; | Part 1 was easy. Couldn't solve part 2 with out [video][Day11-Video] of [Sebastian Aigner][sebi] and [Olaf Gottschalk][olaf].    |
| [12] | [Hill Climbing Algorithm]  | &#9733; | &#9733; | Took a lot of time implementing my first aStart (A*) search in Advent of Code.                                                   |
| [13] | [Distress Signal]          | &#9733; | &#9733; | Needed two tries to solve it. Hard part was to simplify the input, but after solving this it worked.                             |
| [14] | [Regolith Reservoir]       | &#9733; | &#9733; | Falling snow/sand. Very nice puzzle. Solved part 2 by just expanding my grid (brute force?)                                      |
| [15] | [Beacon Exclusion Zone]    | &#9733; | &#9733; | Part 1 was easy, but failed in part 2 because it created all points. Replaced points with ranges this worked!                    |
| [16] | [Proboscidea Volcanium]    | &#9733; | &#9733; | No idea how to solve this. Used solution of [Christoph Kainz][Day16-CK]                                                          |
| [17] | [PyroclasticFlow]          | &#9733; |         | Tetris! Solved part 2 on my own, but ran in many easy avoidable bugs. Part 2 still open.                                         |
| [18] | [Boiling Boulders]         | &#9733; | &#9733; | First 3D puzzle (lava cube). Solved part 1 on my own. Solved part 2 based on [solution from Todd Ginsberg][Day18-TG]             |
| [19] | [Not Enough Minerals]      | &#9733; |         | RTS. Solved part 1 based on [solution from Todd Ginsberg][Day19-TG]                                                              |
| [20] | [Grove Positioning System] | &#9733; |         | not started                                                                                                                      |
| [21] | [Monkey Math]              | &#9733; |         | Solution of part 1 is unusable for solving part 2.                                                                               |
| [22] | [Monkey Map]               |         |         | not started                                                                                                                      |
| [23] | [Unstable Diffusion]       |         |         | not started                                                                                                                      |
| [24] | [Blizzard Basin]           |         |         | not started                                                                                                                      |
| [25] | [Full of Hot Air]          |         |         | not started                                                                                                                      |

## Resources

* [Todd Ginsberg blog][todd]
* [Advent of code Jetbrains Playlist][jetbrains]
* [reddit][reddit]
* [Tobias Bahl Repo][tobi]


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
[tobi]: https://github.com/tobias-bahls/advent-of-code
[jetbrains]: https://www.youtube.com/playlist?list=PLlFc5cFwUnmwxQlKf8uWp-la8BVSTH47J

[Calorie Counting]: https://adventofcode.com/2022/day/1         
[Rock Paper Scissors]: https://adventofcode.com/2022/day/2      
[Rucksack Reorganization]: https://adventofcode.com/2022/day/3  
[Camp Cleanup]: https://adventofcode.com/2022/day/4             
[Supply Stacks]: https://adventofcode.com/2022/day/5            
[Tuning Trouble]: https://adventofcode.com/2022/day/6           
[No Space Left On Device]: https://adventofcode.com/2022/day/7  
[Treetop Tree House]: https://adventofcode.com/2022/day/8       
[Rope Bridge]: https://adventofcode.com/2022/day/9              
[Cathode-Ray Tube]: https://adventofcode.com/2022/day/10         
[Monkey in the Middle]: https://adventofcode.com/2022/day/11     
[Hill Climbing Algorithm]: https://adventofcode.com/2022/day/12  
[Distress Signal]: https://adventofcode.com/2022/day/13          
[Regolith Reservoir]: https://adventofcode.com/2022/day/14       
[Beacon Exclusion Zone]: https://adventofcode.com/2022/day/15    
[Proboscidea Volcanium]: https://adventofcode.com/2022/day/16    
[PyroclasticFlow]: https://adventofcode.com/2022/day/17          
[Boiling Boulders]: https://adventofcode.com/2022/day/18         
[Not Enough Minerals]: https://adventofcode.com/2022/day/19      
[Grove Positioning System]: https://adventofcode.com/2022/day/20
[Monkey Math]: https://adventofcode.com/2022/day/21              
[Monkey Map]: https://adventofcode.com/2022/day/22               
[Unstable Diffusion]: https://adventofcode.com/2022/day/23       
[Blizzard Basin]: https://adventofcode.com/2022/day/24           
[Full of Hot Air]: https://adventofcode.com/2022/day/25          