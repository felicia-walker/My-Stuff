import urllib
import urllib2
import cookielib
import re
import sys

if len(sys.argv) != 5:
    print "Invalid number of parameters!"
    print ""
    print "Usage:  SolvedPuzzles.py <username> <password> <solved filename> <unsolved filename>"
    sys.exit(0)

## Set up cookie handling
cj = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
urllib2.install_opener(opener)
    
## Sign in
print "Getting HTML..."
try:
    params = 'requested=&username=' + sys.argv[1]+ '&password=' + sys.argv[2]
    o = urllib2.urlopen('http://www.welovepuzzles.com/signin/', params)
    html = o.read()
except:
    print ""

## Go to the solved puzzles page
o = urllib2.urlopen("http://www.welovepuzzles.com/my/solvedpuzzles/")
html = o.read()

## Write the HTML out to a file since it is a weird object type that is hard to deal with
print "Parsing HTML..."

f = open('MySolvedPuzzles.html','w')
f.write(html)
f.close()
f = open('MySolvedPuzzles.html','r')

## Iterate through the HTML and parse out the puzzle number, name, and color
puzzles = {}
for line in f:
    matchobj = re.match('.*Puzzle: <a href=\"/permalink/puzzle/([0-9]{1,5})/\">(.*)</a> : \((.*)\).*', line)
    if matchobj:
        puzzles[int(matchobj.group(1))] = ( matchobj.group(2), matchobj.group(3) )
        continue

## Sign out and close our file
o = urllib2.urlopen("http://www.welovepuzzles.com/signout/")
f.close()

#### Open save file if needed
solvedfilename = sys.argv[3]
unsolvedfilename = sys.argv[4]
solvedf = open(solvedfilename,'w')
unsolvedf = open(unsolvedfilename,'w')
    
## Print the puzzle info sorted by number as a CSV file.  Write list of unsolved puzzle
## numbers to a different file.
unsolvedindex = 1
for puzzlenum in sorted(puzzles.keys()):
    ##print str(item) + " (" + puzzles[item][1] + ")" + " - " + puzzles[item][0]
    solvedf.write(str(puzzlenum) + "," + puzzles[puzzlenum][1] + "," + puzzles[puzzlenum][0] + "\n")

## List out any missing numbers between the last puzzle and the current one
    if puzzlenum != unsolvedindex:
       while unsolvedindex < puzzlenum:
            unsolvedf.write(str(unsolvedindex) + "\n")
            unsolvedindex = unsolvedindex + 1
            
            ## Big chunk of missing numbers, so don't include those    
            if unsolvedindex in range(8664,19209):
                unsolvedf.write("Puzzles 8664 to 19208 do not exist\n")
                unsolvedindex = 19209

    unsolvedindex = unsolvedindex + 1

## Close the files
solvedf.close()
unsolvedf.close()