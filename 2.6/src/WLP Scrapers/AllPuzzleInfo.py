import re
import sys
import urllib

if len(sys.argv) != 2:
    print "Invalid number of parameters!"
    print ""
    print "Usage:  AllPuzzleInfo.py <filename>"
    sys.exit(0)

## Open a file to store stuff in
f = open(sys.argv[1],'w')

for index in range(23520, 23540):
    print "Getting HTML for puzzle # " + str(index) + "..."
    
    response = urllib.urlopen('http://www.welovepuzzles.com/permalink/puzzle/' + str(index))
    html = response.readlines()

    for line in html:
        matchobj = re.match('^\s+(.+)</h3>', line)
        if matchobj:
            mymatch = matchobj.group(1)
            if not str(mymatch).startswith("<h3"):
                f.write(str(index) + ",\"" + mymatch + "\"\n")
            continue

f.close()
