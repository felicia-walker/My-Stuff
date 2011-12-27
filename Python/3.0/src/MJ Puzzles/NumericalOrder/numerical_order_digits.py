import copy

## Global constants
N = 16
totalprocessed = 0

#### Main function
if __name__ == '__main__':
               
    # Our stack, init with spaces.  Max size is biggest number with next biggest stuck on the end
    # like this:  x...yx..y.  Need to add extra spaces for double digit numbers.
    stack_length = (2 * N) + 1
    if (N > 9):
        stack_length += 2 * (N - 9)
        
    stack = []
    stack.append([1, ["." for x in range(0, stack_length)]])
    
    # Make a set of solutions so we can list out the unique ones
    solutions = set()
    total_solutions = 0
    
    # Go through the stack until there is nothing left or a solution is found
    doneFlag = False
    while (not doneFlag):
    
        # Exit if the stack is empty
        if (len(stack) == 0):
            #print("Stack is empty!")
            break
    
        # Get the top value from the stack
        curStackValue = stack.pop()
        curNum = int(curStackValue[0])
        curString = curStackValue[1]
    
        ##if (curNum == 2):
        ##    print("Cur stack value: " + str(curStackValue))
    
        # At the end, see if it is a solution then skip further processing
        # It's a solution if there are no spaces or only ones at the ends
        if (curNum == N+1):
            totalprocessed = totalprocessed + 1
            if (totalprocessed % 50000 == 0):
                print ("Processed {0} leaf nodes...".format(totalprocessed))

            numTransitions = 0
            for j in range(1, len(tmpString)):
                if (tmpString[j] == "." and tmpString[j-1] != "."):
                    numTransitions += 1
                if (tmpString[j] != "." and tmpString[j-1] == "."):
                    numTransitions += 1
    
            if (numTransitions < 2):
                solution = ("".join(tmpString)).replace(".","")
                if (solution not in solutions):
                    solutions.add(solution)
                    total_solutions += 1
                    print("Solution: {0}".format(solution))
        
            continue
    
        # Go through the string and insert the digit where possible.
        # Then end index varies on how long the current number to insert is
        endRange = len(curString) - curNum - 1
        if (curNum > 9):
            endRange -= 2

        for i in range(0, endRange):
            startValue = curString[i]
            endIndex = i + curNum + 1
            endValue = curString[endIndex]
            result = (startValue == "." and endValue == ".")
            
            if (curNum > 9):
                startValue = curString[i+1]
                endIndex = i + curNum + 3
                endValue = curString[endIndex]               
                result = result and (startValue == "." and endValue == ".")            
    
            # If we can insert the number, make a new string and push on the stack
            # with the next number to insert
            if (result):
                tmpString = copy.copy(curString)
                
                # If over nine, have to insert the tens and ones separately 
                if (curNum > 9):
                    tmpString[i] = "1"
                    tmpString[endIndex-1] = "1"
                    tmpString[i+1] = str(curNum - 10)
                    tmpString[endIndex] = str(curNum - 10)
                else:
                    tmpString[i] = str(curNum)
                    tmpString[endIndex] = str(curNum)
               
                stack.append([curNum+1, tmpString])
                
print("Total solutions = {0}".format(total_solutions))
