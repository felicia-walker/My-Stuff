# Perplex Assessment Test 5
#
# Problem:
# George has $800. He wants to spend this on movies, computer games, and music. A
# movie costs $10, a song $1, and a game $50. H (Happiness) is a function of the 
# number of songs he buys (s), the number of games (g), and number of movies (m).
#
# H=10M^.9*S^.2*G^1.6
#
# Bearing in mind George does not want to spend more than half his budget on one area
# of entertainment, how many of each product should he buy to maximize this happiness?  
# What is the value of this maximum happiness?
#
# Solution:
# Iterate over each item a number of times, so it doesn't exceed half the budget, and
# calculate the total and happiness. If we don't go over the budget and the happiness is
# larger than the previous, save the new happiness.  
import math

PRICE_SONG = 1
PRICE_GAME = 50
PRICE_MOVIE = 10
BUDGET = 800
HALF_BUDGET = BUDGET / 2

max_h = 0
amounts = [0, 0, 0]

# Calculate the amounts of each item to hit half the budget
movieHalf = math.floor(HALF_BUDGET / PRICE_MOVIE)
gameHalf = math.floor(HALF_BUDGET / PRICE_GAME)
songHalf = math.floor(HALF_BUDGET / PRICE_SONG)

# Iterate over each item, but don't exceed half the budget
for m in range (1, movieHalf + 1):
    for g in range (1, gameHalf + 1):
        for s in range (1, songHalf + 1):
            
            # Calculate the total and make sure it is within the budget
            total = (PRICE_MOVIE * m) + (PRICE_GAME * g) + (PRICE_SONG * s)
            if (total <= 800):
                
                # Calculate the happiness and save if larger than the previous max
                h = 10 * pow(m, 0.9) * pow(s, 0.2) * pow(g, 1.6)
                if (h > max_h):
                    max_h = h
                    amounts = [m, s, g]

# Print out the answer
print("Max happiness is {0}".format(max_h))
print("M = {0[0]}, S = {0[1]}, G = {0[2]}".format(amounts))
