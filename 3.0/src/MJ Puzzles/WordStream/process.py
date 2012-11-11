import sys
import os
import copy

letterindex = lambda x: ord(x) - ord('a')

matchdict = {}
worddict = {}
mainstack = []

def sharedletters(word1, word2):

    # Tally up the letters in word1
    word1dict = {}
    for letter1 in word1:
        word1dict[letter1] = word1dict.get(letter1, 0) + 1
    
    # Tally up the matching letters in word2, but do not count repeat letters
    for letter2 in word2:
        try:
            word1dict[letter2] -= 1
            
            if (word1dict[letter2] < 0):
                word1dict[letter2] = 0
                
        except KeyError:
            pass
    
    # Return the total minus the length of word1
    total = 0
    for entry in word1dict.values():
        total += int(entry)
        
    return len(word1) - total

def processnext(nextletter):
    #print("next " + nextletter)
    if (nextletter =='a'):
        print(mainstack)
        mainstack.pop()
        return
    
    # Iterate through the list of next letters to find a match
    for nextword in worddict[nextletter]:
        # Make a working copy of our stack so far so we can iterate over it
        tmpstack = copy.copy(mainstack)
        
        # Check against every word in our stack so far
        badflag = False
        while ((len(tmpstack) > 0) and not badflag):
            # Make a working copy of our stack so far so we can iterate over it
            tmpstack = copy.copy(mainstack)
    
            prevword = tmpstack.pop()
            matchnum = int(matchdict[prevword[0]][letterindex(nextletter[0])])
            numshared = sharedletters(prevword, nextword)
            
            #print(numshared)
            if (numshared != matchnum):
                #print("Nope {0} to {1} on {2}".format(prevword, nextword, matchnum))
                badflag = True
            #else:
                #print("Matched {0} to {1} on {2}".format(prevword, nextword, matchnum))
        
            # If we everything matched, but the word on the stack and keep going
            if not badflag:
                mainstack.append(nextword)
                #print(mainstack)
                processnext(chr(ord(nextletter) - 1))

if __name__ == '__main__':
    matchfile = open("d:\programming\wordstream.txt", "r")
    
    # Read in the matrix of match numbers into a dictionary of smaller and smaller lists
    index = "z"
    for line in matchfile:
        line = line.rstrip()
        matchdict[index] = str.split(line, " ")
        index = chr(ord(index) - 1)
    
    # Load all of the words into a dictionary based on letter
    for curletter in (chr(i) for i in range(ord('a'), ord('z')+1)):
        #print("Loading words starting with {0}".format(curletter.upper()))
        wordfile = open("d:\programming\\" + curletter + ".txt", "r")
        
        worddict[curletter] = []
        for line in wordfile:
            worddict[curletter].append(line.rstrip())

    # Start at Z since that may result in faster processing
    curletter = "z"
    for word in worddict[curletter]:
        mainstack = []
        mainstack.append(word)
        processnext(chr(ord(curletter) - 1))