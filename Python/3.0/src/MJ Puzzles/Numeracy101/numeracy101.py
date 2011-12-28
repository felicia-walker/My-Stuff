# Numeracy 101
#
# Problem:
# Using the numbers 1-9 once each, can you create a fraction that equals exactly
# one third?
#
# Solution:
# Iterate through a range of numerator values and calculate the denominator by multiplying by three.
# Count the number of times each digit appears in the numerator and denominator.  Ones that end up 
# with a count of one for each digit is a possible solution.  Note, the numerator range was pre-chosen
# based on how it would create a valid denominator.

# This is an array that contains the number of times a digit appears in the possible solution.
# The array index is one less than the corresponding digit.
digitcounts = []
    
# Functions -----------------------------------------------------------------------

# Function to count the digits in the number and update the global count list
# Return true is all is good, false if there is a duplicate
def countdigits(number, countlist):
    failFlag = False
    
    # Go through each digit and update its count. 
    for index in range (0, len(number)):
        countIndex = int(number[index])
        countlist[countIndex] += 1
        
        # If a count is larger than one, set a flag to indicate failure
        # Also fail if there is a zero present since that is invalid based on the problem statement
        if (countlist[countIndex] > 1 or countlist[0] > 0):
            failFlag = True
 
    return (not failFlag)

# Main function --------------------------------------

# Loop through each numerator value.  These are the min and max values to get five digits in denominator
# Do this since denominator is larger by 3x, so probably has five digits
for numerator in range (3124, 9877):
    numerator = str(numerator)
    
    # Initialize the count array
    digitcounts = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

    # Record the digit counts for the numerator.  Bail if the numerator already has duplicate digits.
    for numindex in range(0, len(numerator)):
        if (not countdigits(numerator, digitcounts)):
            continue
        
        # Calculate the denominator 
        denominator = str(int(numerator) * 3)
        
        # Must have a five digit denominator if we are to use all digits 1-9
        if (len(denominator) < 5):
            continue
        
        # Record the digit counts for the denominator. If there are no duplicate digits, we have
        # a solution.
        if (not countdigits(denominator, digitcounts)):
            continue
        else:
            print("Possible solution: ({0}, {1})".format(numerator, denominator))
       
        
