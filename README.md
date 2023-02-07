# sodoku_solver
Solves any given sodoku puzzle.

I may revisit this at some point to make it easier to add new puzzles with a UI as opposed to manually adding them in the code like it is right now.

The algorithm implemented here is really simple. The program will set the value for an unknown box in the puzzle to 1. It will then check that 1 is a valid placement within the rules of sodoku (it is unique in its column, row, and 3x3 box). If the placement is invalid, the box will be reassigned with a value of 2 and then be rechecked. Once a valid placement has been found, the program will do the same with the next box. If a box is found in which there are no valid placements, the last box will be returned to and iterated upon. Eventually, the algorithm will solve the entire puzzle in this manner.
