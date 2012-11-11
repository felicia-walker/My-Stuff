rings = [['n', 'q', 'k', 'e', 's', 'r', 'o', 'h'],
         ['i', 'j', 'i', 'u', 'r', 'f', 'a', 'a'],
         ['c', 'b', 'n', 'l', 'd', 'u', 'w', 'a'],
         ['i', 'r', 'b', 'h', 'g', 'r', 'g', 'e'],
         ['b', 'k', 's', 'n', 'e', 'e', 'r', 'a']]

numRings = len(rings)
ringSize = len(rings[0])
num = 1

def rotateRing(ring):
    tmp = ring[ringSize - 1]
    for i in range(ringSize - 1, 0, -1):
        ring[i] = ring[i - 1]
        
    ring[0] = tmp
    
def printAllRings():
    for i in range(0, numRings):
        print(rings[i])
    
def processRing(ring, word, index):
    global num
    
    for i in range(0, ringSize):
        #print("{0} {1}".format(index, i))
        if index == 2:
            if num == 999:
                printAllRings()
            else:
                print("{0} - {1}".format(num, word + ring[0]))
            num = num + 1
        else:
            processRing(rings[index + 1], word + ring[0], index + 1)
            
        rotateRing(ring)
    
###### Main
processRing(rings[0], "", 0)
    