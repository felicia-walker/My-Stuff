# Numerical Order
#
# Problem:
# Consider this number: 64200246. There are 0 digits between the zeros, two
# digits between the twos, four digits between the fours, and six digits
# between the sixes. Can you create two numbers with the same properties with the
# following sets: 1-8 and 1-16 with two of each number in each set.
#
# Solution:
# Implement a stack to keep track of out current string of numbers and what number we want
# to insert next. The stack lets us to a depth first search. Iterate through the numbers we
# need to insert, and try all possible positions.  We start with the largest number since there
# are fewer places it can be put.

import copy

# Create a factorial lamda to make calculating one easier
fact = lambda x: 1 if x == 0 else x * fact(x - 1)

# Have the user enter the max number, which makes debugging easier with small numbers
##$$TODO - need input validation here
N = int(input("Enter N:"))
total_todo = fact(N)
total_processed = 0
print("Total leaf nodes to process: {0}".format(total_todo))
           
# Create a stack that will hold the current number being inserted as well as the solution so far.
# The solution string needs to be twice the maximum number since there are two of each number. 
# Initialize the stack with our largest number and a solution of only place holders.
stack_length = (2 * N) + 1        
stack = []
stack.append([N, ["." for x in range(0, stack_length)]])

# Make a set of solutions so we can list out the unique ones
solutions = set()
total_solutions = 0

# Go through the stack until there is nothing left or a solution is found
doneFlag = False
while (not doneFlag):

    # Exit if the stack is empty
    if (len(stack) == 0):
        print("Stack is empty!")
        break

    # Get the top value from the stack. This includes the number to try to insert and
    # the string to try inserting into.
    curStackValue = stack.pop()
    curNum = int(curStackValue[0])
    curString = curStackValue[1]

    # No more places to insert, so see if it is a solution.
    # It's a solution if there are no spaces or only ones at the ends
    if (curNum == 0):
        
        # Print out some progress
        total_processed = total_processed + 1
        if (total_processed % 50000 == 0):
            print ("Processed {0} leaf nodes of {1}...".format(total_processed, total_todo))

        # Check for blanks, except for the ends of the string, which is expected.
        if (possibleSolution[1: len(possibleSolution)].count('.') == 0):
            solution = (",".join(possibleSolution)).replace(".", "").lstrip(",").rstrip(",")
            
            # See if we found this one already and add if we have not.
            if (solution not in solutions):
                solutions.add(solution)
                total_solutions += 1
                
                print("Solution: {0}".format(solution))
    
        continue

    # Go through the string and insert the digit where possible.
    # The end index varies on how long the current number to insert to avoid inserting
    # the second number outside the bounds of the string
    endRange = len(curString) - curNum - 1
    
    for i in range(0, endRange):
        startValue = curString[i]
        endIndex = i + curNum + 1
        endValue = curString[endIndex]
        
        # We can only insert if the start and end places are empty
        result = (startValue == "." and endValue == ".")
 
        # If we can insert the number, make a new string and push on the stack
        # with the next number to insert
        if (result):
            possibleSolution = copy.copy(curString)
            possibleSolution[i] = str(curNum)
            possibleSolution[endIndex] = str(curNum)
           
            stack.append([curNum - 1, possibleSolution])
            
# Now that we are done, print out how many total solutions were found
print("Total solutions = {0}".format(total_solutions))
