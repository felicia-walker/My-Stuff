import urllib2
import time
import cookielib
import re
import os

BASE_URL = 'https://www.furaffinity.net'
BASE_DIR = 'c:\Users\Scott\Pictures\Furry\\'
URLS_FILE = BASE_DIR + "pageurls.txt"
INTERVAL = 1

# Set up cookie handling
cj = cookielib.CookieJar()
opener = urllib2.build_opener(urllib2.HTTPCookieProcessor(cj))
urllib2.install_opener(opener)

# Log in using POST data captured with LiveHeaders
try:
    params = 'action=login&retard_protection=1&name=xxx&pass=xxx&login=Login+to%C2%A0FurAffinity'
    urllib2.urlopen(BASE_URL + '/login/', params)
except ValueError:
    print "Could not log in"

# Go through submission pages and collect URLs in a file for later processing if such a file does not exist
if (not os.path.exists(URLS_FILE)):
	subindex = 0
	page_url_file = open(URLS_FILE, 'w')
	url = BASE_URL + '/msg/submissions/old@60/'
	while (len(url) > 0):
		subindex = subindex + 1
		
		# Go to a submissions page
		print ("Opening page " + str(subindex) + "...")
		subpage_data = urllib2.urlopen(url)
		
		# See if there are image names.  Add them to our running list if there are. 
		url = '';
		for line in subpage_data:
			
			viewmatchobj = re.match('.*a href="(/view/[0-9]{7,9}/).*', line)
			nextmatchobj = re.match('.*a class="more"  href="([^"]*)".*', line) 
		
			if nextmatchobj:
				url = BASE_URL + nextmatchobj.group(1)
			
			if viewmatchobj:
				page_url_file.write(BASE_URL + viewmatchobj.group(1) + "\n")
		
	page_url_file.close()

# Go through the URLs file and download each image
page_url_file = open(URLS_FILE, 'r')
for image_url in page_url_file:
	imgpage_data = urllib2.urlopen(image_url)
	for img_line in imgpage_data:
		viewmatchobj2 = re.match('var full_url  = "([^"]*)";', img_line)
		
		if viewmatchobj2:
			full_img_url = viewmatchobj2.group(1)
			viewmatchobj3 = re.match('.*/([0-9]*\.[^/]*)', full_img_url)
			
			if viewmatchobj3:
				filename = viewmatchobj3.group(1)
				if (os.path.exists(BASE_DIR + filename)):
					print ("   Skipping " + filename + " since it exists...")
				else:
					print("   Saving " + filename + "...")
		
					# Open up the image URL and save it was a binary file
					try:
					    imgopener = opener.open(full_img_url)
					    imgfile = open(BASE_DIR + filename, 'wb')
					    img = imgopener.read()
					    imgfile.write(img)
					except Exception:
					    print ("   *** ERROR: Could not download " + full_img_url + " ***")
					    
					imgfile.close()
					imgopener.close()
			else:
				print("   Could not form a filename from " + full_img_url)
			
			time.sleep(1)
			break
			
page_url_file.close()
print ('DONE!')
