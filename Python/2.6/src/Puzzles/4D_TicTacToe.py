import sys
from copy import deepcopy
import numpy
import os

# 2,2,0,2

EMPTY = '.'
X = 'x'
O = 'o'
BOARD_SIZE = 5

def notPlayer(player):
    if (player == X):
        return O
    else:
        return X
        
def printBoard2D(board):
    for i in range(0, BOARD_SIZE):
        for j in range(0, BOARD_SIZE):
            print(board[i][j]),
        print('')
    print('')

def printBoard4D(board):
    for i in range(0, BOARD_SIZE):
        print('Row {0} ---------------'.format(i + 1))
                
        for j in range(0, BOARD_SIZE):
            printBoard2D(board[i][j])
    print('')


def checkSet(mySet, playerChar):
    ret_val = -1
    
    if (mySet.count(playerChar) == (BOARD_SIZE - 1) and mySet.count(EMPTY) == 1):
        ret_val = mySet.index(EMPTY)

    return ret_val

def checkBoard2D(board, playerChar):
    global hasNextMove
    global foundLocation
    
    otherPlayerChar = notPlayer(playerChar)
    newBoard = deepcopy(board)
    lookupBoard = numpy.array(board)
    
    # Rows
    for i in range(0, BOARD_SIZE):
        foundIndex = checkSet(lookupBoard[i, :].tolist(), otherPlayerChar) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (i, foundIndex)
            newBoard[i][foundIndex] = playerChar

    # Cols
        foundIndex = checkSet(lookupBoard[:, i].tolist(), otherPlayerChar) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (foundIndex, i)
            newBoard[foundIndex][i] = playerChar
    
    # Diagonals
    foundIndex = checkSet(numpy.diagonal(lookupBoard).tolist(), otherPlayerChar) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (foundIndex, foundIndex)
        newBoard[foundIndex][foundIndex] = playerChar
        
    foundIndex = checkSet(numpy.diagonal(lookupBoard[::-1]).tolist(), otherPlayerChar) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (BOARD_SIZE - foundIndex - 1, foundIndex)
        newBoard[BOARD_SIZE - foundIndex - 1][foundIndex] = playerChar
                        
    return newBoard

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

# Main --------------------------------------------------------
curBoard = initBoard()
curPlayer = O
hasNextMove = True
foundLocation = ()

while hasNextMove:
    possibleMoves = 0
    
    # Check within each small board
    print("---- 2D Squares ----")
    for bigRowIndex in range(0, BOARD_SIZE):
        for bigColIndex in range(0, BOARD_SIZE):
            hasNextMove = False
            newBoard = checkBoard2D(curBoard[bigRowIndex][bigColIndex], curPlayer)
    
            # If possible move, make the found location 4D and update the board
            if hasNextMove:
                possibleMoves = possibleMoves + 1
                foundLocation = (bigRowIndex, bigColIndex, foundLocation(0), foundLocation(1))
                curBoard[bigRowIndex][bigColIndex] = newBoard
                
    # Check all 3D vertical cubes
    print("---- 3D Vertical Cubes ----")
    for bigColIndex in range(0, BOARD_SIZE):
        diagBoard = []
        altDiagBoard = []
        
        for smallIndex in range (0, BOARD_SIZE):
            hasNextMove = False
            horizBoard = []
            vertBoard = []
            
            # Construct small boards from horizontal and vertical slices
            for bigRowIndex in range(0, BOARD_SIZE):
                tmpBoard = numpy.array(curBoard[bigRowIndex][bigColIndex])
                horizBoard.append(tmpBoard[smallIndex, :].tolist())
                vertBoard.append(tmpBoard[:, smallIndex].tolist())
        
            # Check the vertical boards
            hasNextMove = False
            newBoard = checkBoard2D(vertBoard, curPlayer)
    
            # If possible move, make the found location 4D and update the board
            if hasNextMove:
                possibleMoves = possibleMoves + 1
                foundLocation = (foundLocation(0), bigColIndex, foundLocation(1), smallIndex)
                curBoard[bigRowIndex][bigColIndex] = newBoard
                
            # Check the horizontal boards
            hasNextMove = False
            newBoard = checkBoard2D(horizBoard, curPlayer)
    
            # If possible move, make the found location 4D and update the board
            if hasNextMove:
                possibleMoves = possibleMoves + 1
                foundLocation = (foundLocation(0), bigColIndex, foundLocation(1), smallIndex)
                curBoard[bigRowIndex][bigColIndex] = newBoard
                
            # Add to cube diagonal boards
            diagBoard.append(curBoard[smallIndex][bigColIndex][smallIndex][smallIndex])
            altDiagBoard.append(curBoard[smallIndex][bigColIndex][smallIndex][BOARD_SIZE - smallIndex - 1])
            
        # Check cube diagonals
        foundIndex = checkSet(diagBoard, notPlayer(curPlayer)) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (foundIndex, foundIndex)
            curBoard[foundIndex][bigColIndex][foundIndex][foundIndex] = curPlayer
            
        foundIndex = checkSet(altDiagBoard, notPlayer(curPlayer)) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (BOARD_SIZE - foundIndex - 1, foundIndex)
            curBoard[foundIndex][bigColIndex][BOARD_SIZE - foundIndex - 1][foundIndex] = curPlayer
            
    # Check all 3D horizontal cubes
    print("---- 3D Horizontal Cubes ----")
    for bigRowIndex in range(0, BOARD_SIZE):
        diagBoard = []
        altDiagBoard = []
        
        for smallIndex in range (0, BOARD_SIZE):
            hasNextMove = False
            horizBoard = []
            vertBoard = []
            
            # Construct small boards from horizontal and vertical slices
            for bigColIndex in range(0, BOARD_SIZE):
                tmpBoard = numpy.array(curBoard[bigRowIndex][bigColIndex])
                horizBoard.append(tmpBoard[smallIndex, :].tolist())
                vertBoard.append(tmpBoard[:, smallIndex].tolist())
        
            # Check the vertical boards
            hasNextMove = False
            newBoard = checkBoard2D(vertBoard, curPlayer)
    
            # If possible move, make the found location 4D and update the board
            if hasNextMove:
                possibleMoves = possibleMoves + 1
                foundLocation = (foundLocation(0), bigColIndex, foundLocation(1), smallIndex)
                curBoard[bigRowIndex][bigColIndex] = newBoard
                
            # Check the horizontal boards
            hasNextMove = False
            newBoard = checkBoard2D(horizBoard, curPlayer)
    
            # If possible move, make the found location 4D and update the board
            if hasNextMove:
                possibleMoves = possibleMoves + 1
                foundLocation = (foundLocation(0), bigColIndex, foundLocation(1), smallIndex)
                curBoard[bigRowIndex][bigColIndex] = newBoard
                
            # Add to cube diagonal boards
            diagBoard.append(curBoard[bigRowIndex][smallIndex][smallIndex][smallIndex])
            altDiagBoard.append(curBoard[bigRowIndex][smallIndex][smallIndex][BOARD_SIZE - smallIndex - 1])
            
        # Check cube diagonals
        foundIndex = checkSet(diagBoard, notPlayer(curPlayer)) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (foundIndex, foundIndex)
            curBoard[foundIndex][bigColIndex][foundIndex][foundIndex] = curPlayer
            
        foundIndex = checkSet(altDiagBoard, notPlayer(curPlayer)) 
        if (foundIndex >= 0):
            hasNextMove = True
            foundLocation = (BOARD_SIZE - foundIndex - 1, foundIndex)
            curBoard[foundIndex][bigColIndex][BOARD_SIZE - foundIndex - 1][foundIndex] = curPlayer   
                                 
    # Check the 3D diagonal cubes
    print("---- 3D Main Diagonal Cubs ----")
    diagBoard = []
    altDiagBoard = []
    
    for smallIndex in range (0, BOARD_SIZE):
        hasNextMove = False
        horizBoard = []
        vertBoard = []
        
        # Construct small boards from horizontal and vertical slices
        for bigIndex in range(0, BOARD_SIZE):
            tmpBoard = numpy.array(curBoard[bigIndex][bigIndex])
            horizBoard.append(tmpBoard[smallIndex, :].tolist())
            vertBoard.append(tmpBoard[:, smallIndex].tolist())
    
        # Check the vertical boards
        hasNextMove = False
        newBoard = checkBoard2D(vertBoard, curPlayer)

        # If possible move, make the found location 4D and update the board
        if hasNextMove:
            possibleMoves = possibleMoves + 1
            foundLocation = (foundLocation(0), bigIndex, foundLocation(1), smallIndex)
            curBoard[bigIndex][bigIndex] = newBoard
            
        # Check the horizontal boards
        hasNextMove = False
        newBoard = checkBoard2D(horizBoard, curPlayer)

        # If possible move, make the found location 4D and update the board
        if hasNextMove:
            possibleMoves = possibleMoves + 1
            foundLocation = (foundLocation(0), bigIndex, foundLocation(1), smallIndex)
            curBoard[bigIndex][bigIndex] = newBoard
            
        # Add to cube diagonal boards
        diagBoard.append(curBoard[bigIndex][smallIndex][smallIndex][smallIndex])
        altDiagBoard.append(curBoard[bigIndex][smallIndex][smallIndex][BOARD_SIZE - smallIndex - 1])
        
    # Check cube diagonals
    foundIndex = checkSet(diagBoard, notPlayer(curPlayer)) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (foundIndex, foundIndex)
        curBoard[foundIndex][bigIndex][foundIndex][foundIndex] = curPlayer
        
    foundIndex = checkSet(altDiagBoard, notPlayer(curPlayer)) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (BOARD_SIZE - foundIndex - 1, foundIndex)
        curBoard[foundIndex][bigIndex][BOARD_SIZE - foundIndex - 1][foundIndex] = curPlayer 
 
    print("---- 3D Alternate Diagonal Cubs ----")
    diagBoard = []
    altDiagBoard = []
    
    for smallIndex in range (0, BOARD_SIZE):
        hasNextMove = False
        horizBoard = []
        vertBoard = []
        
        # Construct small boards from horizontal and vertical slices
        for bigIndex in range(0, BOARD_SIZE):
            tmpBoard = numpy.array(curBoard[bigIndex][BOARD_SIZE - bigIndex - 1])
            horizBoard.append(tmpBoard[smallIndex, :].tolist())
            vertBoard.append(tmpBoard[:, smallIndex].tolist())
    
        # Check the vertical boards
        hasNextMove = False
        newBoard = checkBoard2D(vertBoard, curPlayer)

        # If possible move, make the found location 4D and update the board
        if hasNextMove:
            possibleMoves = possibleMoves + 1
            foundLocation = (foundLocation(0), v, foundLocation(1), smallIndex)
            curBoard[bigIndex][BOARD_SIZE - bigIndex - 1] = newBoard
            
        # Check the horizontal boards
        hasNextMove = False
        newBoard = checkBoard2D(horizBoard, curPlayer)

        # If possible move, make the found location 4D and update the board
        if hasNextMove:
            possibleMoves = possibleMoves + 1
            foundLocation = (foundLocation(0), BOARD_SIZE - foundLocation(0) - 1, foundLocation(1), smallIndex)
            curBoard[bigIndex][BOARD_SIZE - bigIndex - 1] = newBoard
            
        # Add to cube diagonal boards
        diagBoard.append(curBoard[bigIndex][smallIndex][smallIndex][smallIndex])
        altDiagBoard.append(curBoard[bigIndex][smallIndex][smallIndex][BOARD_SIZE - smallIndex - 1])
        
    # Check cube diagonals
    foundIndex = checkSet(diagBoard, notPlayer(curPlayer)) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (foundIndex, foundIndex)
        curBoard[foundIndex][BOARD_SIZE - bigIndex - 1][foundIndex][foundIndex] = curPlayer
        
    foundIndex = checkSet(altDiagBoard, notPlayer(curPlayer)) 
    if (foundIndex >= 0):
        hasNextMove = True
        foundLocation = (BOARD_SIZE - foundIndex - 1, foundIndex)
        curBoard[foundIndex][BOARD_SIZE - bigIndex - 1][BOARD_SIZE - foundIndex - 1][foundIndex] = curPlayer 
                          
    # We only want to proceed if there was at least one possible move
    hasNextMove = False
    if (possibleMoves == 0):
        print('No possible moves left')
        
    if (possibleMoves == 1):
        print('Played location was ' + str(foundLocation))
        printBoard4D(newBoard)
        
        curBoard = newBoard
        hasNextMove = True
        
    if (possibleMoves > 1):
        print ('More than one possible move, cannot continue.')
        
    curPlayer = notPlayer(curPlayer)
