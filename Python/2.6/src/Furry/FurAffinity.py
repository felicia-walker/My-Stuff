import urllib2
import cookielib
import re
import os

# Set up cookie handling
cj = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
urllib2.install_opener(opener)

# Log in using POST data captured with LiveHeaders
try:
    params = 'action=login&retard_protection=1&name=xxx&pass=xxx&login=Login+to%C2%A0FurAffinity'
    o = opener.open('https://www.furaffinity.net/login/', params)
    data = o.read()
except ValueError:
    print "HTTP exception"

subindex = 0
quitflag = 0
lastindex = 0
while quitflag == 0:
    subindex = subindex + 1
    
    # Go to a submissions page
    print ("Opening web page " + str(subindex) + " with lastindex " + str(lastindex) + "...")
    if subindex == 1:
        o = opener.open('http://www.furaffinity.net/msg/submissions/old@36/')
    else:
        url = 'http://www.furaffinity.net/msg/submissions/old~' + str(lastindex) + '@36//'
        #print (url)
        o = opener.open(url)
    data2 = o.read()
    o.close()

    # Write HTML to a file and read back in so we get lines properly
    ftmp = open('c:\FATmp.html','w')
    ftmp.write(data2)
    ftmp.close()
    ftmp = open('c:\FATmp.html','r')

    # See if there are image names.  Add them to our running list if there are. Otherwise, set quit flag.
    quitflag = 1
    for line in ftmp:
        #print(line)
        viewmatchobj = re.match('.*a href="/view/([0-9]{7,9})/.*', line)
        if viewmatchobj:
            if viewmatchobj.group(1) == lastindex:
                quitflag = 1
            else:
                quitflag = 0
                lastindex = viewmatchobj.group(1)
    
        #matchobj = re.match('.*(http://[a-z]\.d\.furaffinity\.net/art/.*\.thumbnail.*)\".*', line)
        matchobj = re.match('.*(http://d\.facdn\.net/art/.*\.thumbnail.*)\".*', line)
        if matchobj:

            # Get rid of the thumbnail part
            newurl = matchobj.group(1).replace(".thumbnail", "")
            #print(newurl)

            # Pull out the filename
            matchobj2 = re.match('.*[0-9]{10,13}\.(.*)', newurl)
            filename = matchobj2.group(1)
            print("   Saving (" + str(lastindex) + ") " + filename + "...")

            # Open up the image URL and save it was a binary file
            try:
                imgopener = opener.open(newurl)
                imgfile = open('c:\Users\Scott\Pictures\Furry\\' + filename, 'wb')
                img = imgopener.read()
                imgfile.write(img)
            except Exception:
                print ("   *** ERROR: Could not download " + newurl + " ***")
                
            imgfile.close()
            imgopener.close()
         
    ftmp.close()
os.remove('c:\FATmp.html')
