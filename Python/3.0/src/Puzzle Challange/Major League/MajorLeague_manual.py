rings = [[['n', 'q', 'k', 'e', 's', 'r', 'o', 'h'],
          ['i', 'j', 'i', 'u', 'r', 'f', 'a', 'a'],
          ['c', 'b', 'n', 'l', 'd', 'u', 'w', 'a'],
          ['i', 'r', 'b', 'h', 'g', 'r', 'g', 'e'],
          ['b', 'k', 's', 'n', 'e', 'e', 'r', 'a']],
         [['u', 'e', 'p', 'l', 's', 't', 'y', 'a'],
          ['n', 'i', 'a', 's', 'p', 'v', 'w', 'a'],
          ['a', 'j', 'y', 'b', 'g', 'n', 't', 'o'],
          ['e', 'k', 'r', 'l', 'r', 'r', 'o', 't'],
          ['o', 'm', 'b', 'm', 'r', 'l', 'r', 'e']],
         [['v', 'r', 'u', 'k', 'z', 'm', 'p', 'p'],
          ['i', 'a', 'x', 'e', 'o', 'c', 'a', 'a'],
          ['k', 'r', 'i', 'a', 'g', 's', 'c', 'w'],
          ['a', 'm', 'n', 'b', 'i', 't', 'e', 'y'],
          ['c', 'o', 'r', 'n', 'l', 'a', 'k', 't']],
         [['b', 'c', 'f', 'e', 'l', 't', 'j', 'b'],
          ['c', 'l', 'd', 'n', 'h', 'r', 'r', 'h'],
          ['g', 'x', 'a', 'l', 'o', 'o', 'i', 'b'],
          ['e', 'd', 'a', 'f', 'i', 'i', 'w', 'n'],
          ['e', 'g', 'c', 'n', 'c', 'f', 'g', 'e']]]

numRings = len(rings[0])
ringSize = len(rings[0][0])
ringSet = 0

def rotateRing(ring):
    tmp = ring[ringSize - 1]
    for i in range(ringSize - 1, 0, -1):
        ring[i] = ring[i - 1]
        
    ring[0] = tmp
    
def printAllRings():
    for i in range(0, numRings):
        print(rings[ringSet][i])
    
###### Main
quitFlag = False

print("Ringset (0-3):")
ringSet = input()

while not(quitFlag):

    printAllRings()
    print()
    
    print("Ring:")
    num = input()

    if (num == 'Q' or num == 'q'):
        quitFlag = True
    else:
        rotateRing(rings[ringSet][int(num)])