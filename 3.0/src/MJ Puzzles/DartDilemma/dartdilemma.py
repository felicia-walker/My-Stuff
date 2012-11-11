# Dart Dilemma
#
# Problem: How many combinations are there for three darts to score exactly seven points if each dart scores?
#          Assume the order of darts does not matter.
# 
# To add to seven, we can only use 1-4 for the points. Easiest way is to have each dart loop through these
# points, add the result, and save the ones that add to seven. Obviously will not work so well for larger
# number of darts of point range.

POINTS_TARGET = 7
NUM_DARTS = 3
MAX_POINTS = POINTS_TARGET - NUM_DARTS + 1
MAX_MULTIPLIER = 3

solutions = set()

# Start with dart one 
for dart1 in range (1, MAX_POINTS + 1):
    for mult1 in range(1, MAX_MULTIPLIER + 1):
        total1 = dart1 * mult1
        
        # Dart two only has to go to one less than the max point per dart since dart #1 has at least
        # one point
        for dart2 in range (1, MAX_POINTS):
            for mult2 in range(1, MAX_MULTIPLIER + 1):
                total2 = dart2 * mult2
                
                # If we have hit the points total, no need to try dart 3
                if (total1 + total2 >= POINTS_TARGET):
                    continue
    
                # Dart three only has to go to two less than the max point per dart since dart #1 and 
                # 2 has at least one point each
                for dart3 in range (1, MAX_POINTS - 1):
                    for mult3 in range(1, MAX_MULTIPLIER + 1):
                        total3 = dart3 * mult3
                        total = total1 + total2 + total3

                        # See if we have a solution. Save it if we do.
                        if (total == POINTS_TARGET):
                            # Used for debugging
                            #print(tmpTotal)
                            #print("{0} {1} {2}".format(dart1, dart2, dart3))
                            #print("{0} {1} {2}".format(mult1, mult2, mult3))
                            #print("")
                            
                            # Save the points and multipliers
                            darts = [(dart1, mult1), (dart2, mult2), (dart3, mult3)]
                            darts.sort()
                            darts_string = "{0[0][0]}{0[1][0]}{0[2][0]}, {0[0][1]}{0[1][1]}{0[2][1]}".format(darts)
                             
                            solutions.add(darts_string)
         
# Print the answer
print("Number of solutions is " + str(len(solutions)))
print("")
print("Solutions (points, multipliers)")               
print(solutions)
