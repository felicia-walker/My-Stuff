fin = open('c:\when.txt','r')
text = []
cipher = (4, 43, 42, 101, 49, 48, 79, 17, 250)
cipher2 = (42, 7, 21, 73, 51, 102, 42, 89, 47, 48, 37, 29, 21, 31, 58, 38, 42, 36, 35, 73, 51, 52, 189)
cipher3 = (5, 250, 59, 18, 24, 35, 33, 28, 53, 79, 77, 49, 42, 31, 10, 48, 50, 56, 250, 78)

for line in fin:
    line = line.rstrip()
    
    if len(line) > 0:
        words = line.split(' ')

        for word in words:
            text.append(word)
    
fin.close()

decrypted=""
print "--------------"
for index in cipher:
    decrypted += text[index-1][0]
    print "%i %s" % (index, text[index - 1])
decrypted += "zz"
print "z z"
for index in cipher2:
    decrypted += text[index-1][0]
    print "%i %s" % (index, text[index - 1])
decrypted += "y"
print "y"
for index in cipher3:
    decrypted += text[index-1][0]
    print "%i %s" % (index, text[index - 1])

print decrypted    