# Lost Letters
#
# Problem:
# Put the tiles together into sets of three to form five English words. However,
# two of the tiles are red herrings, which two?
#
# Solution:
# This is difficult to do all by computer, but we can make a list of words that is easy to 
# manually inspect.
#
# Use a web scraper to get a list of words that start and end with each tile. Go through
# this list and filter out the words that contain sequences that do not match our tiles.
# From this list we can then manually figure out the answer.
#
# The web scraper is a separate program to make debugging easier.

import re
import sys
import os

# Go through the word list and regex out bad words.
wordfile = open('.\lostletters.txt', 'r')

print("Processing...");
for line in wordfile:
    
    # A bad word is one that does not contain sequences from three tiles
    linematchobj = re.match("(ble|and|eni|po|rm|in|ss|kst|mi|ing|ign|ac|ant|ta|ous|sh|gen){3}", line)

    if linematchobj:
        print("Word found: {0}".format(line))

wordfile.close()
