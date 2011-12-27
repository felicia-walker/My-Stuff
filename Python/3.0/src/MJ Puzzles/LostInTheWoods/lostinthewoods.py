# Lost In The Woods
#
# Problem:
# Move in a straight line N, S, E, W the number engraved on your square. Land on a tile
# containing a white number and you must only move NE, SE, NW, SW until you land on another
# white numbered square, then you will revert back to N, S, E, W.  If you can reach the end 
# square, the path to freedom will be revealed.
#
# Solution:
# Do a depth first search goverened by a maximum depth variable. Use a text file of numbers
# where numbers larger than ten represent white numbers.  These numbers are the original value
# plus ten.

import copy

# Constants
START_ROW = 0
START_COL = 0
END_VALUE = 99
DIRECTIONS = (("n", "e", "s", "w"), ("ne", "se", "sw", "nw"))
MAX_DEPTH = 20
MAX_COUNT = pow(4, MAX_DEPTH + 1)
LOOP_THRESHOLD = 2

# Global variables
maze = []
loop_counts = []
finalpath = []
curCount = 0
    
# Functions --------------------------------------------

# This function is the main recursive function that navigates to the next node on
# our path. It uses a loop counter to see if we are stuck in a loop.
def pathMove(curRow, curCol, curDepth, curPath, curOrientation, curLoopCounts):

    # Get the current value and up our loop counter
    curValue = int(maze[curRow][curCol])
    curLoopCounts[curRow][curCol] += 1
    
    # Exit if we've passed the loop threshold, which means we are probably stuck in a loop
    if (curLoopCounts[curRow][curCol] > LOOP_THRESHOLD):
        return 0
        
    # Adjust orientation and value if the value is larger than 10
    if (curValue > 9 and curValue != END_VALUE):
        curValue -= 10
        nextOrientation = 1
    else:
        nextOrientation = 0
        
    # This selects the set of directions we are using from an array of directions
    nextOrientation = abs(curOrientation - nextOrientation)
        
    # Iterate through our set of directons and move that way.  Orientation is the index
    # for the set to use.
    for direction in DIRECTIONS[nextOrientation]:
        
        # If we have hit the max depth, update our progress counter and print out if needed
        if (curDepth == MAX_DEPTH):
            global curCount
            curCount += 1
            
            if (curCount % 10000 == 0):
                print("Processed {0} paths out of {1} max paths so far...".format(curCount, MAX_COUNT))

        # Get the new value indexes
        newRow = curRow
        newCol = curCol
       # print ("{0} {1} {2} {3}".format(curRow, curCol, direction, curValue))
 
        # Find the new value based on these indexes
        if (direction == "n"):
            newRow -= curValue
        elif (direction == "nw"):
            newRow -= curValue
            newCol -= curValue
        elif (direction == "w"):
            newCol -= curValue
        elif (direction == "sw"):
            newRow += curValue
            newCol -= curValue
        elif (direction == "s"):
            newRow += curValue
        elif (direction == "se"):
            newRow += curValue
            newCol += curValue
        elif (direction == "e"):
            newCol += curValue
        elif (direction == "ne"):
            newRow -= curValue
            newCol += curValue
        else:
            print("Invalid direction {0}!!!".format(direction))
            continue
        
        #print("New row,col {0},{1} with orientation {2}".format(newRow,newCol,nextOrientation))
        #print(curLoopCounts)
        
        # Don't use new direction if out of bounds
        if (newRow < 0 or newCol < 0 or newRow > mazesize_row or newCol > mazesize_col):
            continue
        
        # Don't use new direction if we're at the current depth or higher and have no solution yet
        if (curDepth >= MAX_DEPTH):
            continue
        
        # Get the new value since we're inside the maze
        newValue = int(maze[newRow][newCol])
        
        # Update our path tracking since this is the solution
        newPath = []
        newPath.extend(curPath)
        newPath.append(direction)
        
        #print("New value {0} - Direction {1}: curDepth {2}, newPath {3}".format(newValue, direction, curDepth, newPath))

        # If the new value is an end char, exit out with the final path
        if (newValue == END_VALUE):
            global finalpath
            finalpath = []
            finalpath.extend(newPath)
            return 1
       
        # Make a copy of our loop counts to pass to our recursive function call
        nextLoopCounts = copy.deepcopy(curLoopCounts)
       
        # Move to the new spot and start over.  If we get a success result back, exit out with same result.
        result = pathMove(newRow, newCol, curDepth + 1, newPath, nextOrientation, nextLoopCounts)
        if (result):
            return 1
       
    return 0

# Main function --------------------------------------------
    
# Read the maze file into our grid
mazefile = open("lostinthewoods.txt", "r")

for line in mazefile:
    splitline = line.rstrip().split(" ")
    tmpMazeRow = []
    
    for item in splitline:
       tmpMazeRow.append(item)
        
    maze.append(tmpMazeRow)
    
mazefile.close()

# Store our max sizes for easier use later
mazesize_row = len(maze) - 1
mazesize_col = len(maze[0]) - 1

# Create a loop count array the same size as the maze. This array stores how many times
# we have passed through a particular place in the maze.  Pass over too many times, and
# it probably means we are in a loop.
loop_counts = [[0 for c in range(0, mazesize_col + 1)] for r in range(0, mazesize_row + 1)]

# Start looking and interpret the result
result = pathMove(START_ROW, START_COL, 0, finalpath, 0, loop_counts)
if (result):
    print("Solution found: {0}".format(finalpath))
else:
    print("No solution found with depth {0}.".format(MAX_DEPTH))
        
