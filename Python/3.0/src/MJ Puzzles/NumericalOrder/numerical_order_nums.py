import copy

#### Main function
if __name__ == '__main__':
    
    fact = lambda x: 1 if x==0 else x * fact(x-1)

    ##$$TODO - need input validation here
    N = int(input("Enter N:"))
    total_todo = fact(N)
    total_processed = 0
    print("Total leaf nodes to process: {0}".format(total_todo))
               
    # Our stack, init with spaces.  Max size is biggest number with next biggest stuck on the end
    # like this:  x...yx..y.  Need to add extra spaces for double digit numbers.
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
        if (curNum == 0):
            total_processed = total_processed + 1
            if (total_processed % 50000 == 0):
                print ("Processed {0} leaf nodes or {1}...".format(total_processed, total_todo))

            numTransitions = 0
            for j in range(1, len(tmpString)):
                if (tmpString[j] == "." and tmpString[j-1] != "."):
                    numTransitions += 1
                if (tmpString[j] != "." and tmpString[j-1] == "."):
                    numTransitions += 1
    
            if (numTransitions < 2):
                solution = (",".join(tmpString)).replace(".","").lstrip(",").rstrip(",")
                if (solution not in solutions):
                    solutions.add(solution)
                    total_solutions += 1
                    print("Solution: {0}".format(solution))
        
            continue
    
        # Go through the string and insert the digit where possible.
        # Then end index varies on how long the current number to insert is
        endRange = len(curString) - curNum - 1
        for i in range(0, endRange):
            startValue = curString[i]
            endIndex = i + curNum + 1
            endValue = curString[endIndex]
            result = (startValue == "." and endValue == ".")
     
            # If we can insert the number, make a new string and push on the stack
            # with the next number to insert
            if (result):
                tmpString = copy.copy(curString)
                
                # If over nine, have to insert the tens and ones separately 
                tmpString[i] = str(curNum)
                tmpString[endIndex] = str(curNum)
               
                stack.append([curNum-1, tmpString])
                
print("Total solutions = {0}".format(total_solutions))
