# Puffin Parade
#
# Problem:
# The proud puffins always like to celebrate in a parade by marching in equal rows.
# However they encountered a problem when one puffin went missing leaving, them one 
# puffin short. They tried their usual formation of ten in each row, but found it 
# left one empty space, so they reformed and tried nine in each row, but again it 
# left one empty space and eleven would not do. After trying 2, 3, 4, 5, 6, 7 and 8
# in each row and each time finding it left one empty space they decided to line up
# single file, making it the longest parade they ever did have.
#
# Can you tell me what the minimum number of puffins in the parade must be?
#
# Solution:
# Iterate over a number of total puffins that are divisible by the original row amount
# of ten. Subtract one from this and divide by 2 through 9, then see if the remainder
# is one less than this number.  That means there is one hole in the last row, which is
# what we want. If all of these have one hole, a valid solution has been found.

import math

# From the problem statement, the minimum number must be ten. Also define a maximum amount to govern
# how far to test.
MIN_AMT = 10
MAX_AMT = 30000
MAX_PER_ROW = 10

# From the problem statement, each solution must be a multiple of this since it would result in an 
# integer multiple of rows with 10 in them.
for orig_amt in range (MIN_AMT, MAX_AMT + 1, MIN_AMT):
    test_amt = orig_amt - 1

    # Iterate over all possible numbers in each row up to the 10. We won't go beyond this since
    # the problem statement says 11 would not do. That indicates it was not tested.  
    failFlag = False
    for row_amt in range (2, MAX_PER_ROW + 1):
        rem = test_amt % row_amt
        #print ("Total = {0}, row number is {1}, remainder is {2}".format(test_amt, row_amt, rem))

        # See if there is an incomplete row with only one missing.  If not, this solution is invalid so exit.
        if (rem != row_amt - 1):
            failFlag = True
            break

    # This is a possible solution, so let's check before printing it
    if (not failFlag):
        #print ("Possible solution is {0}".format(test_amt))

        # Check the solution by finding the number of holes in the last row and making sure it is one
        failFlag = False
        for row_amt in range (2, MIN_AMT + 1):
            remainder = test_amt % row_amt
            numHoles = row_amt - remainder
            #print("{0} per row gives a remainder of {1} and {2} holes".format(row_amt, remainder, numHoles))       
            
            if (numHoles != 1):
                failFlag = True
                break
        
        # This is a valid solution if the fail flag is not set, so print it and stop
        if (not failFlag):
            print("Solution is " + str(test_amt + 1))
            break
