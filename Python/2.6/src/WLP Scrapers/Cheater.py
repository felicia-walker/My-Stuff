import urllib
import urllib2
import cookielib
import re
import sys

if len(sys.argv) != 4:
    print "Invalid number of parameters!"
    print ""
    print "Usage:  Cheater.py <username> <password> <logfile>"
    sys.exit(0)

## Set up cookie handling
cj = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
urllib2.install_opener(opener)
    
## Sign in
print "Signing in..."
try:
    params = 'requested=&username=' + sys.argv[1]+ '&password=' + sys.argv[2]
    o = urllib2.urlopen('http://www.welovepuzzles.com/signin/', params)
    html = o.read()
except:    
    print ""

## Open the log file
f = open(sys.argv[3],'w')
    
## Go through each puzzle and try each of the four choices

for index in range(19209, 23461):
##for index in range(5426, 5431):
    print "Examining HTML for puzzle # " + str(index) + "..."
    response = urllib.urlopen('http://www.welovepuzzles.com/permalink/puzzle/' + str(index))
    html = response.readlines()

    ## Pull out the GUID
    for line in html:
        matchobj = re.match('.*value=\"([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})', line)
        
        if matchobj:
            guid = matchobj.group(1)
            continue

    ## Press each radio button, exit on success
    correctflag = 0
    for buttonnum in range(897, 901):
        print "   Trying choice " + str(buttonnum)
        params = 'RADIO=' + str(buttonnum) + '&BUTTON-=&submitform=Check%20Answer&puzzleid=' + str(index) + '&guid=' + guid
        o = urllib2.urlopen('http://www.welovepuzzles.com/webservice/markpuzzle/ajax/?noCache=1212687920078', params)
        html = o.readlines()

        ## Pull out the flag, 0 = correct, 1 = incorrect
        for line in html:
            matchobj = re.match('.*CDATA\[([01])\]', line)

            if matchobj:
                if matchobj.group(1) == '0':
                    print "Correct - Choice " + str(buttonnum - 896)
                    f.write(str(index) + "," + str(buttonnum - 896) + "\n")
                    correctflag = 1
                break

        ## Exit if correct answer found
        if correctflag == 1:
            break

    ## Print an error if nothing was accepted
    if correctflag == 0:
        print "NO ANSWERS ACCEPTED!!!"
        f.write(str(index) + ",none\n")

f.close()
        