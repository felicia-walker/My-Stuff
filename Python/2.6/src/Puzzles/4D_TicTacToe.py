import sys
from copy import deepcopy
import numpy

EMPTY = '.'
X = 'x'
O = 'o'

singleTest = [[O, O, EMPTY, EMPTY, O],
          [O, EMPTY, EMPTY, O, EMPTY],
          [EMPTY, EMPTY, X, X, X],
          [O, O, EMPTY, EMPTY, EMPTY],
          [O, O, EMPTY, EMPTY, X]]

BOARD_SIZE = len(multiTest)

hasNextMove = True

def notPlayer(player):
    if (player == X):
        return O
    else:
        return X
        
def printBoard2D(board):
    sys.stdout.write('Played location was ' + str(foundLocation) + '\n')
    for i in range(0, BOARD_SIZE):
        for j in range(0, BOARD_SIZE):
            sys.stdout.write(board[i][j])
        sys.stdout.write('\n')
    sys.stdout.write('\n')

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

curPlayer = X
curBoard = oneone
while hasNextMove:
    hasNextMove = False
    newBoard = checkBoard2D(curBoard, curPlayer)
    
    if hasNextMove:
        curBoard = newBoard
        printBoard2D(curBoard)
    
    curPlayer = notPlayer(curPlayer)
