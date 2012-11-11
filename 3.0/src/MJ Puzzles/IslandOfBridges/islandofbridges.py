# Island of Bridges
#
# Problem:
# Starting from anywhere on the isle, how many different ways are there of crossing the eight
# bridges without crossing a bridge twice, and which route is the shortest?
#
# Solution:
# Model the isle as a graph and iterate through all possible paths. Recursively travel each
# node and record the paths that go over all bridges without backtracking.

import copy

# Define the graph, A=0, B=1, C=2, D=3.  Each tuple is a path to the next node.
graph = [[1, 2, 3], [0, 2, 3, 3], [0, 1, 3, 3], [0, 1, 1, 2, 2]]
start_node = 0
end_node = 3

# Variables to store the solutions
total_paths = 0
paths = []

# This is the main recursive function that figures out the next node to go to.
def processNode(node, curGraph, curPath):
    global total_paths

    # Add the current node to the current path
    curNode = curGraph[node]
    curPath.append(node)
    
    # See if we've hit the end.  If so, see if this is a valid solution.
    if (node == end_node):
        endFlag = True

        # If there are untraveled bridges in our current graph, this is not a valid solution
        for n in curGraph:
            if (len(n) > 0):
                endFlag = False
                break
        
        # This is a valid solution, so record it
        if (endFlag):
            total_paths += 1
            paths.append(curPath)
            print("Solution: " + str(curPath))
            return

    # For the current node, iterate through all bridges that have not been used so far
    for next in curNode:
        #print("Next - " + str(next))
        
        # Make a copy of our path so far and its graph, but remove the bridge we just used.
        nextGraph = copy.deepcopy(curGraph)
        nextGraph[node].remove(next)
        nextGraph[next].remove(node)
        #print(nextGraph)

        nextPath = copy.copy(curPath)
        #print(nextPath)
        
        # Process the next node using this new path and graph
        processNode(next, nextGraph, nextPath);

# Main program ------------------------------------------
path = []
processNode(start_node, graph, path)
    
print("Total number of paths is " + str(total_paths))
