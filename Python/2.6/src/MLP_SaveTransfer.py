# My Little Pony: Friendship Is Magic save file transfer utility
#
# This program will decrypt an MLP save from one device and encrypt it for use on another device.
# You will need the GLUIDs from both devices and some means of transferring files to them.
# Right now you have to put the filenames and GLUIDs into this program. I'll clean that up
# at some point to make it more user friendly.
#
# See http://roundcube3.blogspot.com/2012/11/reverse-engineering-with-ponies.html for details.
# This is also the source I obtained the info needed to write this program.
#
# To use:
# 1. Install the XXTEA library from https://pypi.python.org/pypi/xxtea
# 2. Enter the filenames and GLUIDs (remove the spaces) to use below
# 3. Run the program

import struct, xxtea

WORD_SIZE = 4

file_old = open("C:\mlp_save.dat", "rb")
file_new = open("C:\mlp_save_new.dat", "wb")
gluid = "1ee4688454e7a830e44491c86a1140fc137b6c204d60742c5cdb0bb1e8eb0465453aaa43"
gluid_new = "3c7675febad2a6448446f083cb4a837f7d52d3fdabf16a38afeed478865b17db128669a6"
gluid_hex = gluid.decode("hex")
gluid_hex_new = gluid_new.decode("hex")

try:
    data = file_old.read()
finally:
    file_old.close()

data_size = len(data)  
uncomp_size = data[0:WORD_SIZE]
comp_size = data[WORD_SIZE:2 * WORD_SIZE]
encry_size = data[2 * WORD_SIZE:3 * WORD_SIZE]
encry_data = data[3 * WORD_SIZE:data_size - WORD_SIZE]
reserved = data[data_size - WORD_SIZE:len(data)]

decry_data = xxtea.decrypt(encry_data, gluid_hex, False)
new_encry_data = xxtea.encrypt(decry_data, gluid_hex_new, False)

try:
    file_new.write(uncomp_size)
    file_new.write(comp_size)
    file_new.write(encry_size)
    file_new.write(new_encry_data)
    file_new.write(reserved)
finally:
    file_new.close()
