MAX = 10

#### Main function
if __name__ == '__main__':
               
    for n in range (MAX, 10000000, MAX):
        total = n - 1
    
        badflag = False
        for nn in range (2, MAX):
            rem = total % nn
            #print ("Total = {0}, row number is {1}, remainder is {2}".format(total, nn, rem))
    
            if (rem != nn - 1):
                badflag = True
                #print
                break
    
        # Print the solution
        if (not badflag):
            print ("Possible solution is {0}".format(total))

            # Check the solution
            doneFlag = True
            for nn in range (2, MAX + 1):
                mainpart = total / nn
                mainmult = mainpart * nn
                difference = total - mainmult
                print("{0}: Main is {1}, mult is {2}, difference is {3}".format(nn, mainpart, mainmult, difference))
                
                if (nn - difference != 1):
                    doneFlag = False
                    continue
            
            if (total % (MAX + 1) == 0):
                doneFlag = False
            
            if (doneFlag):
                print("Solution is " + str(total))
                break