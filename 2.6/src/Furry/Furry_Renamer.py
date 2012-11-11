import os
import re
import sys

# Use the directory provided at the command line, othewise use a default dir
if (len(sys.argv) == 2):
    topdir = sys.argv[1]
else:
    topdir = "C:\Users\Scott\Pictures\Furry"

# Walk the directory to get all the yummy information    
mywalk = os.walk(topdir)
flag = 0

# Iterate over each directory found
for dir in mywalk:

    # Get the file's directory and parent directory    
    dirpath = dir[0]
    path = dirpath.split('\\')
    parentdir = path[len(path) - 1]
 
    # We don't want to process any subdirs at this time
    if (dirpath == topdir):
        print "Processing: " + dirpath

        # Get the list of files in this directory    
        files = dir[2]
        artist = ""

        # Iterate over each file in this directory        
        for file in files:
  
            # See if we have an original FurAffinity filename (timestamp optional)           
            filematch = re.match('[0-9]*\.{0,1}([a-zA-Z0-9]+)[\.\-_](.*)', file)
     #       filematch = re.match('[0-9]*\.{0,1}Furry(.*)', file2)

            # If we had a match, get the artist and original filename
            newname = "";
            if filematch:
                artist = filematch.group(1)
                newname = filematch.group(2)
                
            # If an artist name was not found, try the parent directory name as
            # the artist name
            newname2 = ""
            if (len(artist) == 0):
                artist = parentdir.lower()

                # Try to match on this possibly new artist name
                filematch2 = ""
                filematch2 = re.match(artist + '[_-]{0,1}(.*)', file)

                # If we got a match, pull out the filename only                
                if filematch2:            
                    newname2 = filematch2.group(1)

            # If a filename was found using the new artist, use that.  Otherwise
            # use what we originally found
            if (len(newname2) > 0):                    
                newname = newname2

            # Process the file if we have something to rename it to                
            if (len(newname) > 0):

                # See if an artist directory exists.  Create it if it does not.
                artist_dir = topdir + "\\" + artist
                if (os.path.exists(artist_dir)) == 0:
                    try:
                        os.mkdir(artist_dir)
                    except:
                        print ("Problem creating directory " + artist_dir)

                # Try to rename the file and move it into the artist directory                
                try:
                    print "Renaming " + file + " to " + newname
                    os.rename(dirpath + "\\" + file, artist_dir + "\\" + newname)
                except:
                    print "Could not rename" + dirpath + "\\" + file