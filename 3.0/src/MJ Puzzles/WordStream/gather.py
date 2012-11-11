import urllib.request
import urllib.parse
import re
import sys
import os

if __name__ == '__main__':

    ## First, scape for words of 6 letters exactly. Put in files for later processing.
     
    # Make a list of words and store for later
    for startletter in (chr(i) for i in range(ord('a'), ord('z')+1)):
        wordfile = open('D:\Programming\\' + startletter + '.txt','w')
        
        for endletter in (chr(i) for i in range(ord('a'), ord('z')+1)):
            url = "http://design215.com/toolbox/wordfind.php"
    
            values = {}
            values['fsize'] = "6"
            values['check2'] = ""
            values['fstart'] = startletter
            values['fend'] = endletter
            values['fcontain'] = ""
            values['fscramble'] = ""
            values['rad1'] = "w1"
            values['dx'] = "Volvox Webscraper/1.0; Python 3.0"
            data = urllib.parse.urlencode(values)
    
            headers = {}
            headers['User-Agent'] = "Volvox Webscraper/1.0; Python 3.0"
            headers['Host'] = "design215.com"
            headers['Content-Type'] = "application/x-www-form-urlencoded"
            
            # Open the word page
            print ("Getting words starting with \"{0}\" and ending with \"{1}\"...".format(startletter, endletter))
            req = urllib.request.Request(url, data, headers)
            response = urllib.request.urlopen(req)
            data = str(response.read()).replace("\\n","\n")
     
            # Grab any sub page links
            datasplit = data.split("\n")
            for line in datasplit:
                linematchobj = re.finditer('(' + startletter + '[a-zA-Z]{4}' + endletter + ')<br', line)
               
                if linematchobj:
                     for match in linematchobj:
                        print(match.group(1))
                        wordfile.write(match.group(1) + "\n")
                        
    wordfile.close()    
