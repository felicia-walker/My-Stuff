# Lost Letters - Word scraper
#
# This is a web scraping program that makes use of a site that can return words based
# on certain criteria. We make an HTTP request to this site and filter out the words
# we want from the returned HTML. The words as saved to a file for use by the main program.

import urllib.request
import urllib.parse
import re
import sys
import os

url = "http://design215.com/toolbox/wordfind.php"
wordfile = open('.\lostletters.txt', 'w')
tiles = ("ble", "and", "eni", "po", "rm", "in", "ss", "kst", "gen",
         "mi", "ing", "ign", "ac", "ant", "ta", "ous", "sh")

# Go through all combinations of start and end tiles.
for starttile in tiles:
    for endtile in tiles:
        
        # We can't use the same tile for the start and end
        if (starttile != endtile):

            # Construct our web request
            values = {}
            values['fsize'] = "9"
            values['check2'] = "on"
            values['fstart'] = starttile
            values['fend'] = endtile
            values['fcontain'] = ""
            values['fscramble'] = ""
            values['rad1'] = "w1"
            values['dx'] = "Volvox Webscraper/1.0; Python 3.0"
            data = urllib.parse.urlencode(values)

            headers = {}
            headers['User-Agent'] = "Volvox Webscraper/1.0; Python 3.0"
            headers['Host'] = "design215.com"
            headers['Content-Type'] = "application/x-www-form-urlencoded"
            
            # Open the word page with this request and get the response
            print ("Getting words starting with \"{0}\" and ending in \"{1}\"...".format(starttile, endtile))
            req = urllib.request.Request(url, data, headers)
            response = urllib.request.urlopen(req)
            data = str(response.read()).replace("\\n", "\n")

            # Grab the words from the results section.  The filtering is easier since we know what the start 
            # and end letters are.
            datasplit = data.split("\n")
            for line in datasplit:
                linematchobj = re.finditer(starttile + '[a-zA-Z]{2,3}' + endtile, line)
               
                # For each match, print it out and save to our file.
                if linematchobj:
                    for match in linematchobj:
                        print(match.group())
                        wordfile.write(match.group() + "\n")
                    
wordfile.close()
