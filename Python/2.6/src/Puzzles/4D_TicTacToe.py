import numpy
import os
import sys

# Coords are (ROW, COL, row, col) or (z, row, col)

EMPTY = '.'
X = 'x'
O = 'o'
BOARD_SIZE = 5
INVALID = -1

def notPlayer(player):
    if (player == X):
        return O
    else:
        return X
        
def printSquare(board):
    for i in range(0, BOARD_SIZE):
        for j in range(0, BOARD_SIZE):
            print(board[i][j]),
        print('')
    print('')

def printBoard4D(board):
    for bigRow in range(0, BOARD_SIZE):
        for row in range(0, BOARD_SIZE):
            for bigCol in range(0, BOARD_SIZE):
                for col in range(0, BOARD_SIZE):
                    sys.stdout.write(board[bigRow][bigCol][row][col])
                sys.stdout.write('|')
            print('')
        print('-' * (pow(BOARD_SIZE, 2) + BOARD_SIZE))

def initBoard():
    f_in = open(os.getcwd() + '\\4dBoard.txt', 'r') 
    bigBoard = []
    bigRow = []
    colCount = 0
   
    for line in f_in:
        smallBoard = []
        lineArray = []
    
        # Convert line string into chars
        for char in line:
            lineArray.append(char)
        
        # Convert 1D array into 2D
        for i in range(0, BOARD_SIZE):
            smallBoard.append(lineArray[(i * BOARD_SIZE):(BOARD_SIZE * (i + 1))])
    
        # Add small board to small board row
        colCount = colCount + 1
        bigRow.append(smallBoard)
            
        # If small board row is full, start a new one
        if (colCount == BOARD_SIZE):
            colCount = 0
            bigBoard.append(bigRow)
            bigRow = []
          
    f_in.close()
    return bigBoard

def checkSet(mySet, playerChar):
    ret_val = INVALID
    
    if (mySet.count(playerChar) == (BOARD_SIZE - 1) and mySet.count(EMPTY) == 1):
        ret_val = mySet.index(EMPTY)

    return ret_val

def checkSquare(board, playerChar):
    otherPlayerChar = notPlayer(playerChar)
    lookupBoard = numpy.array(board)
    foundLocation = (INVALID, INVALID)
    
    # Rows and cols
    for i in range(0, BOARD_SIZE):
        #print("        Processing square index " + str(i))
        rowSet = lookupBoard[i, :].tolist()
        foundIndex = checkSet(rowSet, otherPlayerChar)
        if (foundIndex != INVALID):
            foundLocation = (i, foundIndex)
        
        colSet = lookupBoard[:, i].tolist()
        foundIndex = checkSet(colSet, otherPlayerChar)
        if (foundIndex >= 0):
            foundLocation = (foundIndex, i)

    # Diagonals
    foundIndex = checkSet(numpy.diagonal(lookupBoard).tolist(), otherPlayerChar) 
    if (foundIndex != INVALID):
        foundLocation = (foundIndex, foundIndex)
        
    foundIndex = checkSet(numpy.diagonal(lookupBoard[::-1]).tolist(), otherPlayerChar) 
    if (foundIndex != INVALID):
        revFoundIndex = BOARD_SIZE - foundIndex - 1
        foundLocation = (revFoundIndex, foundIndex)
                        
    #print(foundLocation)
    return foundLocation

def checkCube(board, playerChar):
    lookupBoard = numpy.array(board)
    foundLocation = (INVALID, INVALID, INVALID)
    diag = []
    adiag = []
    bdiag = []
    cdiag = []
    
    # Slices
    for i in range(0, BOARD_SIZE):
        #print("    Processing cube index " + str(i))
        #print("        Processing z slice...")
        zSlice = lookupBoard[i, :, :].tolist()
        result = checkSquare(zSlice, playerChar)
        if (result[0] != INVALID):
            foundRow = result[0]
            foundCol = result[1]
            foundLocation = (i, foundCol, foundRow)
            
        rowSlice = lookupBoard[:, i, :].tolist()
        #print("        Processing row slice...")
        result = checkSquare(rowSlice, playerChar)
        if (result[0] != INVALID):
            foundRow = result[0]
            foundCol = result[1]
            foundLocation = (foundRow , i, foundCol)
        
        colSlice = lookupBoard[:, :, i].tolist()
        #print("        Processing col slice...")
        result = checkSquare(colSlice, playerChar)
        if (result[0] != INVALID):
            foundRow = result[0]
            foundCol = result[1]
            foundLocation = (foundRow , foundCol, i)

        # Add to diagonals
        antiI = BOARD_SIZE - i - 1
        diag.append(board[i][i][i])
        adiag.append(board[i][antiI][i])
        bdiag.append(board[i][i][antiI])
        cdiag.append(board[i][antiI][antiI])

    # Check the diagonals
    #print("    Processing diag 1...")
    otherPlayerChar = notPlayer(playerChar)
    foundIndex = checkSet(diag, otherPlayerChar)
    if (foundIndex != INVALID):
        foundLocation = (foundIndex, foundIndex, foundIndex)

    #print("    Processing diag 2...")
    foundIndex = checkSet(adiag, otherPlayerChar)
    if (foundIndex != INVALID):
        foundLocation = (foundIndex, BOARD_SIZE - foundIndex - 1, foundIndex)
        
    #print("    Processing diag 3...")
    foundIndex = checkSet(bdiag, otherPlayerChar)
    if (foundIndex != INVALID):
        foundLocation = (foundIndex, foundIndex, BOARD_SIZE - foundIndex - 1)
                
    #print("    Processing diag 4...")
    foundIndex = checkSet(cdiag, playerChar)
    if (foundIndex != INVALID):
        foundLocation = (foundIndex, BOARD_SIZE - foundIndex - 1 , BOARD_SIZE - foundIndex - 1)  
                                  
    #print(foundLocation)
    return foundLocation

# Main --------------------------------------------------------
curBoard = initBoard()
curPlayer = O
hasNextMove = True
iteration = 0

while hasNextMove:
    foundLocation = (INVALID, INVALID, INVALID, INVALID)
    diagCube = []
    altDiagCube = []
            
    print("Iteration {0}, player {1}".format(str(iteration), curPlayer))
    for i in range(0, BOARD_SIZE):
        lookupBoard = numpy.array(curBoard)
        
        #print("Processing hypercube index " + str(i))
        rowCube = lookupBoard[i, :, :, :]
        result = checkCube(rowCube, curPlayer)
        if (result[0] != INVALID):
            foundRow = result[1]
            foundCol = result[2]
            foundZ = result[0]
            foundLocation = (i, foundZ, foundRow, foundCol)
        
        colCube = lookupBoard[:, i, :, :]
        result = checkCube(colCube, curPlayer)
        if (result[0] != INVALID):
            foundRow = result[1]
            foundCol = result[2]
            foundZ = result[0]
            foundLocation = (foundZ, i, foundRow, foundCol)
                    
        # Add to diagonals
        revIndex = BOARD_SIZE - i - 1
        diagCube.append(curBoard[i][i])
        altDiagCube.append(curBoard[i][revIndex])
    
    # Check diagonals
    #print("Processing 4D diagonal 1...")
    result = checkCube(diagCube, curPlayer)
    if (result[0] != INVALID):
        foundRow = result[1]
        foundCol = result[2]
        foundZ = result[0]
        foundLocation = (foundZ, foundZ, foundRow, foundCol)
    
    #print("Processing 4D diagonal 2...")
    result = checkCube(altDiagCube, curPlayer)
    if (result[0] != INVALID):
        foundRow = result[1]
        foundCol = result[2]
        foundZ = result[0]
        foundLocation = (foundZ, BOARD_SIZE - foundZ - 1, foundRow, foundCol)
        
    # We only want to proceed if there was at least one possible move
    hasNextMove = False
    if (foundLocation[0] == INVALID):
        print('No possible moves left')     
    else:
        print('Played location was ' + str(foundLocation) + ' by ' + curPlayer)
        curBoard[foundLocation[0]][foundLocation[1]][foundLocation[2]][foundLocation[3]] = curPlayer
        hasNextMove = True
        #printBoard4D(curBoard)

    curPlayer = notPlayer(curPlayer)
    iteration = iteration + 1
    
print("DONE!")

#===============================================================================
# O    (2, 2)    (2, 0)
# X    (0, 0)    (4, 0)
# O    (2, 0)    (4, 0)
# X    (2, 1)    (4, 1)
# O    (3, 1)    (4, 1)
# X    (3, 3)    (4, 1)
# O    (2, 2)    (4, 1)
# X    (4, 2)    (4, 1)
#===============================================================================
