# Maze-Solver-

Choose and implement a data structure which can represent maps such as the one in the example. It must provide the necessary infrastructure for finding a shortest path from the start to the finish.
Add a simple parser which can read a map like the one in the example from an input file. It needs to determine the width and height and the locations of the start and finish square as well as the rocks. The structure of the files will look like in the example, i.e., use ‘.’/’0’/’S’/’F’ for empty (ice) squares, rocks, the start and the finish.
Your parser should be able to handle all input files which have this format. We will provide benchmark examples for your performance analysis, but you may also want to create some yourself to test your implementation.
Choose and implement an algorithm which finds a shortest path from the start to the finish in any given map, if one exists (all the benchmarks we provide will have a solution). It should output all the steps of the solution it found, e.g., for the example above:
1. Start at (10,1)
2. Move left to (7,1)
3. Move down to (7,2)
4. Move left to (6,2)
5. Move down to (6,10)
6. Move right to (8,10)
7. Move up to (8,8)
8. Move right to (9,8)
9. Move up to (9,6)
10. Move left to (3,6)
11. Move up to (3,1)
12. Move left to (1,1)
13. Move down to (1,2)
14. Move right to (4,2)
15. Move down to (4,3)
16. Move left to (2,3)
17. Move down to (2,5)
18. Done!
Where the squares are numbered left to right, top to bottom.
