# Emerald Maze
#
# Problem:
# After collecting the gold, the idol laughed and the floor below me broke away. I plunged down into
# a room filled with emeralds.  I knew immediatley I was in the infamous emerald maze.  The rules were
# simple, I cold only move the number of steps that was engraved on the tile I stood on. I could only move
# in a single compass direction (ie: N, NE, E, SE, S, SW, W, NW) until I landed on my next tile.
# To exit the maze and win the emeralds, my last step must be on a tile marked X and I must not step on
# an 'X tile' beforehand or I would be entombed in the room forever. I noticed the tile I stood on was
# marked with a Red 3. I looked round and knew immediatley the route I must take.
#
# Solution:
# Have maze in an external file. Use X to mark ending spots, and use Y as space holders.
# Do a depth first search with a limiting variable to avoid loops and long paths.

# Constants
START_ROW = 11
START_COL = 11

END_CHAR = "X"
INVALID_CHAR = "Y"
DIRECTIONS = ("n", "ne", "e", "se", "s", "sw", "w", "nw")

MAX_DEPTH = 9
MAX_COUNT = pow(8, MAX_DEPTH + 1)

# Global variables
maze = []
finalpath = []
curPathCount = 0

# Functions -----------------------------------------------------------------------
    
# This function checks the final path to make sure we didn't cross an ending character before
# landing on the final one.  This means we only have to check the adjacent value on the path
# since the maximum number of end characters in a row is tw.
def checkFinalPath(direction, endRow, endCol):
    
    prevCol = endCol
    prevRow = endRow
    
    # Get the value of the adjacent cell on the path
    if (direction == "n"):
        prevRow = endRow + 1
    elif (direction == "nw"):
        prevRow = endRow + 1
        prevCol = endCol + 1
    elif (direction == "w"):
        prevCol = endCol + 1
    elif (direction == "sw"):
        prevRow = endRow - 1
        prevCol = endCol + 1
    elif (direction == "s"):
        prevRow = endRow - 1
    elif (direction == "se"):
        prevRow = endRow - 1
        prevCol = endCol - 1
    elif (direction == "e"):
        prevCol = endCol - 1
    elif (direction == "ne"):
        prevRow = endRow + 1
        prevCol = endCol - 1
    else:
        print("Invalid direction on final path check: {0}!!!".format(direction))
        return 0
    
    prevValue = str(maze[prevRow][prevCol])
    
    # Return 1 only if we did NOT pass over an exit char
    if (prevValue == END_CHAR):
        return 0
    else:
        return 1
    
# Moves us to a new location on the map. This is used recursively
def pathMove(curRow, curCol, curDepth, curPath):

    # Loop through each direction and move that way
    for direction in DIRECTIONS:
        
        # If we hit the max depth, record this for progress purposes.
        if (curDepth == MAX_DEPTH):
            global curPathCount
            curPathCount += 1
            
            # Print out our progress every 10000 paths
            if (curPathCount % 10000 == 0):
                print("Processed {0} paths out of {1} max paths so far...".format(curPathCount, MAX_COUNT))

        # Get the new location indexes
        newRow = curRow
        newCol = curCol
        curValue = int(maze[curRow][curCol])
        
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
        
        # Don't use new direction if out of bounds
        if (newRow < 0 or newCol < 0 or newRow > mazesize_row or newCol > mazesize_col):
            continue
        
        # Don't use new direction if we're at the current depth or higher and have no solution yet
        if (curDepth >= MAX_DEPTH):
            continue
        
        # Get the new value since we're inside the maze
        newValue = str(maze[newRow][newCol])
        
        # Update our path tracking since this is the solution
        newPath = []
        newPath.extend(curPath)
        newPath.append(direction)
                        
        # If the new value is an invalid location, exit
        if (newValue == INVALID_CHAR):
            continue

        # If the new value is an end char, exit out with the final path
        if (newValue == END_CHAR):
            global finalpath
            finalpath = []
            finalpath.extend(newPath)
            
            # Make sure we don't cross an end char on our way out.  Exit if we do, continue otherwise.
            result = checkFinalPath(direction, newRow, newCol)
            if (result):
                return 1
            else:
                continue
        
        # Move to the new spot and start over.  If we get a success result back, exit out with same result.
        result = pathMove(newRow, newCol, curDepth + 1, newPath)
        if (result):
            return 1
       
    return 0

# Main program ------------------------------------------------------------------

# Read the maze file into our grid
mazefile = open("emerald_maze.txt", "r")

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

# Start looking and interpret the result
result = pathMove(START_ROW, START_COL, 0, finalpath)
if (result):
    print("Solution found: {0}".format(finalpath))
else:
    print("No solution found with depth {0}.".format(MAX_DEPTH))
    
