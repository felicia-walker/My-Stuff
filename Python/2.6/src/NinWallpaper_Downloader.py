import urllib2
import re
import os

BASE_URL = "http://beta.media.nin.com/gallery/index?g_type=token&g_val=1318&g_sort=newest&g_tag=&g_media=photo&page="
ITEM_URL = "http://beta.media.nin.com/item/?g_media=photo&_sort=newest&g_tag=&g_type=token&g_val=1318&item_id=ITEM_ID"
FLICKR_URL = "http://www.flickr.com/photos/nineinchnails/ID_NUM/sizes/o/in/photostream/"
IMAGE_URL = "http://farm5.static.flickr.com/4045/ID_NUM_"

itemNums = []

flickrNums = []
flickrTitles = []

def getItems(pagenum):
	curCollectionUrl = BASE_URL + str(pagenum)

	# Open a page and get the data
	try:
		print "Processing collection " + str(pagenum)
		data = urllib2.urlopen(curCollectionUrl)
	except ValueError:
		print "HTTP exception"
	
	# Iterate through the entries on the page
	for line in data:
		itemMatchObj = re.match('.*item_id=([0-9]{5}).*', line)
		if itemMatchObj:
			itemNums.append(itemMatchObj.group(1))

def getFlickrNum(itemid):
	curItemUrl = ITEM_URL.replace("ITEM_ID",itemid)

	# Open the page and get the data
	try:
		print "  * Processing item " + str(itemid)
		data = urllib2.urlopen(curItemUrl)
	except ValueError:
		print "HTTP exception"

	# Iterate through the entries on the page
	titleFound = False
	for line in data:
		itemMatchObj = re.match('.*href=\"(.+)\">view full size', line)
		#itemMatchObj = re.match('.*24299512@N03/([0-9]+)/\"><img src=\"http.*', line)
		if itemMatchObj:
			url = itemMatchObj.group(1)
			flickrNums.append(url)
			print "\tURL: " + url

		itemMatchObj = re.match('(.*)</div>', line)
		if itemMatchObj and titleFound:
			title = str(itemMatchObj.group(1)).strip()
			flickrTitles.append(title)
			titleFound = False
			print "\tTitle: " + title
			
		itemMatchObj = re.match('.*id=\"item-title\">', line)
		if itemMatchObj:
			titleFound = True
	
	title = title.replace(' ', '')
	for sym in (':', ';', "'", '"'):
		title = title.replace(sym, '_')
	getImage(url, title)

def getImage(url, title):
	imgname = 'c:\\Users\\Scott\\Pictures\\Nin\\' + title + '.jpg'
	counter = 1
	while os.path.exists(imgname):
		imgname = 'c:\\Users\\Scott\\Pictures\\Nin\\' + title + str(counter) + '.jpg'
		counter = counter +1
		
	print "\tDownloading to " + imgname + "...",

	# Open up the image URL and save it was a binary file
	try:
		imgfile = open(imgname, 'wb')
		imgsrc = urllib2.urlopen(url)
		img = imgsrc.read()
		imgfile.write(img)	
		imgfile.close()
	except Exception as (errno, strerr):
		print ("   *** ERROR: {0} {1}".format(errno, strerr))
	else:
		print "done!"
	
#def getImageOld(flickrNum, title):
#	print "\tGetting number " + flickrNum + ", " + title
#
#	flickrUrl = FLICKR_URL.replace("ID_NUM", flickrNum)
#	curImgUrl = IMAGE_URL.replace("ID_NUM", flickrNum)
#				
#	# Open the page and get the data
#	try:
#		print "\tProcessing Flickr num " + str(flickrNum)
#		data = urllib2.urlopen(flickrUrl)
#	except ValueError:
#		print "HTTP exception"
#
#	# Iterate through the entries on the page
#	for line in data:
#		itemMatchObj = re.match('.*/' + flickrNum + '_(.+_o\.jpg).*', line)
#		if itemMatchObj:
#			curImgUrl = curImgUrl + itemMatchObj.group(1)
#			break
#			
#	# Open up the image URL and save it was a binary file
#	#try:
#	print "\tURL: " + curImgUrl
#	imgfile = open('c:\Users\Scott\Pictures\Nin\\' + title + '.jpg', 'wb')
#	imgsrc = urllib2.urlopen(curImgUrl)
#	img = imgsrc.read()
#	imgfile.write(img)	
#	imgfile.close()
#	#except Exception:
#	#   print ("   *** ERROR: Could not download " + curImgUrl + " ***")

# Main ------------------------------------------------------------------
for pagenum in range(1,8):
	getItems(pagenum)

for itemnum in itemNums:
	getFlickrNum(itemnum)

#for index in range(0,len(flickrNums)):
#	getImage(flickrNums[index],flickrTitles[index])
	
print "Finished!"