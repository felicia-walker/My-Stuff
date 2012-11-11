import os

dir = "D:\Documents and Settings\Scott\My Documents\My Pictures\Furry\Baron Engel"

file = open('d:\\baron.txt', 'r')
for line in file:
    line = line.strip()
    names = line.split(',')
    oldname = names[1]
    newname = names[0]

    print "Renaming " + oldname + " to " + newname
    try:
         os.rename(dir + "\\" + oldname, dir + "\\" + newname)
    except:
        print "Could not rename" + dir + "\\" + oldname
    
file.close()    