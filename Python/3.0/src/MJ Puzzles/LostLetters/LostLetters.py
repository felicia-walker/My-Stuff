# Lost Letters
#
# Problem:
# Put the tiles together into sets of 3 to form 5 English words. However,
# 2 of the tiles are red herrings, which 2?
#
# Solution:
#

import urllib.request
import urllib.parse
import re
import sys
import os

#### First, scape for words 9 letters or less starting and ending
#### with all combox of tiles.  Put in a file for later processing.

##wordfile = open('D:\Programming\lostletters.txt','w')
##tiles = ("ble", "and", "eni", "po", "rm", "in", "ss", "kst", "gen",
##         "mi", "ing", "ign", "ac", "ant", "ta", "ous", "sh")
##
### Make a list of words and store for later
##for starttile in tiles:
##    for endtile in tiles:
##        if (starttile != endtile):
##            url = "http://design215.com/toolbox/wordfind.php"
##
##            values = {}
##            values['fsize'] = "9"
##            values['check2'] = "on"
##            values['fstart'] = starttile
##            values['fend'] = endtile
##            values['fcontain'] = ""
##            values['fscramble'] = ""
##            values['rad1'] = "w1"
##            values['dx'] = "Volvox Webscraper/1.0; Python 3.0"
##            data = urllib.parse.urlencode(values)
##
##            headers = {}
##            headers['User-Agent'] = "Volvox Webscraper/1.0; Python 3.0"
##            headers['Host'] = "design215.com"
##            headers['Content-Type'] = "application/x-www-form-urlencoded"
##            
##            # Open the word page
##            print ("Getting words starting with \"{0}\" and ending in \"{1}\"...".format(starttile, endtile))
##            req = urllib.request.Request(url, data, headers)
##            response = urllib.request.urlopen(req)
##            data = str(response.read()).replace("\\n","\n")
##
##            # Grab any sub page links
##            datasplit = data.split("\n")
##            for line in datasplit:
##                #print(line)
##                linematchobj = re.finditer(starttile + '[a-zA-Z]{2,3}' + endtile, line)
##               
##                if linematchobj:
##                    for match in linematchobj:
##                        print(match.group())
##                        wordfile.write(match.group() + "\n")
##                    
##wordfile.close()    

## Go through the word list and regex out bad words
wordfile = open('lostletters.txt', 'r')

print("Processing...");
for line in wordfile:
    #print(line)
    linematchobj = re.match("(ble|and|eni|po|rm|in|ss|kst|mi|ing|ign|ac|ant|ta|ous|sh|gen){3}", line)

    if linematchobj:
        print("Word found: {0}".format(line))

wordfile.close()
