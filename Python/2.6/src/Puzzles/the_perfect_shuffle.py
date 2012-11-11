import sys

#-----------------------------------------------------------
def Shuffle (pDeck):
    # Shuffle the deck by dividing in two, then alternating starting with
    # the top pile

    newDeck = { }
    
    for intIndex in range(25, -1, -1):
        newDeck[(intIndex * 2) + 1] = pDeck[intIndex]
        newDeck[intIndex * 2] = pDeck[intIndex + 26]

    return newDeck

def PrintDeck (pDeck):
    # Print out the contents of a deck showing both halves

    for intIndex in range(0, 52):
        if (intIndex == 26):
            print
            
        sys.stdout.write(pDeck[intIndex] + ' ')
    print
    print

    return 0

#-----------------------------------------------------------
# Main program
#-----------------------------------------------------------

# Define the initial deck
astrDeckInitial = ('6H','JH','5H','9D','QS','JC','7S','AC','8C','10H','7D','2H', \
                   '5C','7H','AH','KH','8H','4S','2D','4C','4D','QH','2C', \
                   '8S','KS','4H','KD','6D','10C','3C','6S','10S','7C','QD','10D', \
                   '6C','QC','AD','JS','9H','JD','8D','KC','3D','2S','3H', '3S', '5S', \
                   '9C','9S','5D','AS')

print "Initial deck:"
PrintDeck(astrDeckInitial)

# Shuffle six times, showing the results of each
astrDeckNew= astrDeckInitial

for intIteration in range(1, 7):
    astrDeckNew = Shuffle(astrDeckNew)

    print "Shuffle " + `intIteration` + ":"
    PrintDeck(astrDeckNew)

