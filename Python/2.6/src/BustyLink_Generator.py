import re

## Open our files
base_dir = "c:\\documents and settings\\scott\\desktop\\"
f_in = open(base_dir + 'furrystuff.txt','r')
f_out = open(base_dir + 'bbp.html','w')

f_out.write("<html>\n")
f_out.write("<body>\n")
f_out.write("<ul>\n")

## Go through each line looking for an asterisk starter
for line in f_in:
        ##print("Line - " + line)
        viewmatchobj = re.match('\* (.*)', line)
        if viewmatchobj:
            name = viewmatchobj.group(1)
            name = re.sub(' ', '_', name)
            letter = name[0].lower()
            ##print ("Name - " + name)

            final = "<li><a href=\"http://www.freeones.com/html/" + letter + "_links/" + name + "/\">" + name + "</a></li>\n"
            f_out.write(final)

f_out.write("</ul>")
f_out.write("</html>\n")
f_out.write("</body>\n")

f_in.close()
f_out.close()
