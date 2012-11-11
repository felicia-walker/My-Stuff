import sys

#-----------------------------------------------------------

def printData (pData):
    # Pretty printing functions

    for rowIndex in range(0, len(pData)):
        for colIndex in range(0, len(pData[0])):
            sys.stdout.write(pData[rowIndex][colIndex])                
        print

    return 0

def GenerateLetters(pStartX, pStartY):
    # Generate a letter grid from a particular start location
    
    aOutput = [ ]
    for rowIndex in range(pStartX, len(aData), 3):

        if (rowIndex+1 < len(aData)-1 and rowIndex+2 < len(aData)):
            aOutputRow = [ ]
            
            for colIndex in range (pStartY, len(aData[0]), 2):

                if (colIndex+1 < len(aData[0])):
                    strKey = ""
                    strKey += aData[rowIndex][colIndex]
                    strKey += aData[rowIndex][colIndex+1]
                    strKey += aData[rowIndex+1][colIndex]
                    strKey += aData[rowIndex+1][colIndex+1]
                    strKey += aData[rowIndex+2][colIndex]
                    strKey += aData[rowIndex+2][colIndex+1]

                    try:
                        aOutputRow.append("%s" % (dictBraille[strKey]))
                    except KeyError:
                        aOutputRow.append(".")
                
            aOutput.append(aOutputRow) 

    print "Start location (%s,%s):" % (pStartX,pStartY)
    print
    printData(aOutput)
    print "-"*30
    print
    
    return 0

#-----------------------------------------------------------
# Main program
#-----------------------------------------------------------
fileBraille = open('braile.txt','r')
dictBraille = { }

for curFileRow in fileBraille.readlines( ):
    curFileRow = curFileRow.replace('\n','')
    curFileRowSplit = curFileRow.split(',')
    
    dictBraille[curFileRowSplit[1]] = curFileRowSplit[0]

# Open the file of braille letters and read into an array
fileData = open('data.txt','r')
aData = [ ]

for curFileRow in fileData.readlines( ):
    curFileRow = curFileRow.replace('\n','')

    aDataRow = [ ]
    for curChar in curFileRow:
        curChar = curChar.replace('.','*')
        curChar = curChar.replace(' ','.')
        aDataRow.append(curChar)

    aData.append(aDataRow)

# Find letters
GenerateLetters(0,0)
GenerateLetters(0,1)
GenerateLetters(1,0)
GenerateLetters(1,1)
GenerateLetters(2,0)
GenerateLetters(2,1)

