#### Function to count the digits in the number and update the global count list
#### Return true is all is good, false if there is a duplicate
def countdigits(number, countlist):
    badFlag = False
    
    # Go through each digit and update its count. 
    for index in range (0, len(number)):
        countIndex = int(number[index])
        countlist[countIndex] += 1
        
        # If a count is larger than one, set a flag to indicate failure
        # Also fail if there is a zero present since that is invalid
        if (countlist[countIndex] > 1 or countlist[0] > 0):
            badFlag = True
 
    return (not badFlag)

#### Main function
if __name__ == '__main__':
               
    # Loop through each numerator value.  These are the min and max values to get five digits in denominator
    # Do this since denominator is larger by 3x, so probably has five digits
    for numerator in range (3124, 9877):
        numerator = str(numerator)
        
        # Initialize the digit count list
        digitcounts = []
        for i in range(0, 10):
            digitcounts.append(i)
            digitcounts[i] = 0

        # Record the digit counts for the numerator.  Bail on duplicate digits.
        for numindex in range(0, len(numerator)):
            if (not countdigits(numerator, digitcounts)):
                continue
            
            # Calculate the denominator 
            denominator = str(int(numerator) * 3)
            
            # Bail if it is not at least five digits
            if (len(denominator) < 5):
                continue
            
            # Record the digit counts for the denominator. Bail on duplicate digits.
            if (not countdigits(denominator, digitcounts)):
                continue
            else:
                print("Possible solution: ({0}, {1})".format(numerator, denominator))
           
        