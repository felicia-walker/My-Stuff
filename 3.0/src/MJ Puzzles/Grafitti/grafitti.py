# Graffiti
#
# Problem:
# It seems the fairytale gang tried to graffiti their logo on the lab's tiled floor. Luckily
# they were interrupted and only managed to paint a large green circle. Wexler noted that the
# circle did not lie on any of the tiles edges, nor did it touch an intersection between four 
# tiles. The circle is 27 feet in radius and the tiles are 9 inches by 9 inches. The cleaner 
# can replace the ruined tiles at a rate of $7.50 every 15 minutes worked. It will take him 15 
# minutes to replace 4 tiles. Also the tiles can only be bought in boxes of 10 at $11.2 per box.
# What is the maximum Wexler will have to pay?
#
# Solution:
# See how many tiles are covered by half the circle by dividing the X axis into tiles, then iterating
# over them to find out how many tiles are in that column. Add them up, multiply by two. To cover all
# possibilities, offset the center of the circle a number of times.  

import math

OFFSET_STEPS = 100
RADIUS_FT = 27
TILE_SIZE_IN = 9
TILES_IN_RADIUS = int((RADIUS_FT * 12) / TILE_SIZE_IN)
TILE_COST = 7.5
LABOR_COST = 11.2
TILES_PER_LABOR = 4
TILES_IN_BOX = 10

# Place to store the solution's statistics
solution = [0, 0, 0, 0]

# Move the center of the circle around a quadrant near the center. We don't need to do the other
# quadrants due to symmetry.
print ("Processing...")
for x0 in range (0, OFFSET_STEPS):
    for y0 in range (0, OFFSET_STEPS):
        xoffset = x0 / OFFSET_STEPS
        yoffset = y0 / OFFSET_STEPS
        total = 0
        
        # For one quarter of the circle, find the number of tiles it touches. Do this by iterating over
        # the X axis and calculating how many tiles that column are contained in the circle, including
        # the two that the circle's edge may touch
        for x in range(0, TILES_IN_RADIUS):
            ytop = math.sqrt((TILES_IN_RADIUS ** 2) - ((x - xoffset) ** 2)) + yoffset
            ybottom = math.sqrt((TILES_IN_RADIUS ** 2) - ((x - xoffset) ** 2)) - yoffset
            
            # Ceiling the numbers to account for the tiles the circle's edge is on
            ytop_ceil = math.ceil(ytop)
            ybottom_ceil = math.ceil(ybottom)
            
            # If the ceiling equals our raw number, we are at the center of the circle, so stop
            if ((ytop_ceil == ytop) or (ybottom_ceil == ybottom)):
                break
            
            # The total number is double since we only did one semicircle so far
            total += 2 * (ytop_ceil + ybottom_ceil)
            
        laborcost = LABOR_COST * (math.ceil(total / TILES_PER_LABOR))
        materialcost = TILE_COST * (math.ceil(total / TILES_IN_BOX))
        totalcost = laborcost + materialcost
            
        # If this new cost is larger, replace the previous solution with this one
        if (totalcost > solution[3]):
            solution = [xoffset, yoffset, total, totalcost]
        
# Print the solution
print("Max number of tiles for offset ({0[0]},{0[1]}) is {0[2]} with cost ${0[3]}".format(solution))
