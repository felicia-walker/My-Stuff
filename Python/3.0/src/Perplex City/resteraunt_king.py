import sys
#from resteraunt_king_node import *

g_dictNodes = {}
g_decTotalCost = 9999
g_FinalRoute = []

class NodeInfo:
# Make a structure to store the link and cost information

    pass

class Node:
# A working node that can be iterated

    global g_dictNodes
    
    def __init__(self, pName, pDepth = 0):
        self.totalsofar = 0
        self.depth = pDepth
        self.name = pName
        self.PrevNodes = []

    def Iterate(self):    
        global g_decTotalCost
        
        if self.depth == 4:
            print self.totalsofar + g_dictNodes[self.name].neighbors["Ace Bar"]

            decToAceBar = g_dictNodes[self.name].neighbors["Ace Bar"]
            if self.totalsofar + decToAceBar < g_decTotalCost:
                g_decTotalCost = self.totalsofar + decToAceBar
                print "xxx"
                del g_FinalRoute[:]
                for strCurName in self.PrevNodes:
                    g_FinalRoute.append(strCurName)
                g_FinalRoute.append(self.name)

            return
        
        for strNodeName, decNodeValue in g_dictNodes[self.name].neighbors.iteritems():
            if not (strNodeName in self.PrevNodes):
                NextNode = Node(strNodeName, self.depth + 1)
                NextNode.totalsofar = self.totalsofar + decNodeValue

                # Copy the previous node list and add the current node
                for strName in self.PrevNodes:
                    NextNode.PrevNodes.append(strName)
                NextNode.PrevNodes.append(self.name)

                NextNode.Iterate()



# Set up the nodes and their info
g_dictNodes["Ace Bar"] = NodeInfo()
g_dictNodes["Ace Bar"].number = 1
g_dictNodes["Ace Bar"].neighbors = {"Spark's Steak House": 10.15, \
                                    "Corner Bistro": 3.3, \
                                    "Zum Schneider": 6.55, \
                                    "Lombardi's": 1.25}

g_dictNodes["Spark's Steak House"] = NodeInfo()
g_dictNodes["Spark's Steak House"].number = 2
g_dictNodes["Spark's Steak House"].neighbors = {"Ace Bar": 10.15, \
                                                "Corner Bistro": 12.4, \
                                                "Zum Schneider": 9.55, \
                                                "Lombardi's": 10}

g_dictNodes["Corner Bistro"] = NodeInfo()
g_dictNodes["Corner Bistro"].number = 3
g_dictNodes["Corner Bistro"].neighbors = {"Ace Bar": 3.3, \
                                          "Spark's Steak House": 12.4, \
                                          "Zum Schneider": 4.45, \
                                          "Lombardi's": 6.05}

g_dictNodes["Zum Schneider"] = NodeInfo()
g_dictNodes["Zum Schneider"].number = 4
g_dictNodes["Zum Schneider"].neighbors = {"Ace Bar": 6.55, \
                                          "Spark's Steak House": 9.55, \
                                          "Corner Bistro": 4.45, \
                                          "Lombardi's": 8}

g_dictNodes["Lombardi's"] = NodeInfo()
g_dictNodes["Lombardi's"].number = 5
g_dictNodes["Lombardi's"].neighbors = {"Ace Bar": 1.25, \
                                       "Spark's Steak House": 10, \
                                       "Corner Bistro": 6.05, \
                                       "Zum Schneider": 8}

# Start at node 1 and iterate through all nodes back to node 1
nodeFirst = Node("Ace Bar")
nodeFirst.Iterate()

print `g_decTotalCost`
print g_FinalRoute


