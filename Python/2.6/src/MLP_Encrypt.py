# My Little Pony: Friendship Is Magic save file encryption utility
#
# This program will fully encrypt an MLP XML file into one that can be installed on a device.
# Right now you have to put the filenames and GLUID (remove the space) into this program. 
# I'll clean that up at some point to make it more user friendly.
#
# See http://roundcube3.blogspot.com/2012/11/reverse-engineering-with-ponies.html for details.
# This is also the source I obtained the info needed to write this program.
#
# To use:
# 1. Install the XXTEA library from https://pypi.python.org/pypi/xxtea
# 2. Enter the filenames and GLUID (remove the spaces) to use below
# 3. Run the program
#
# Notes:
# 1. The inner key may change on the next update

import struct, xxtea, zlib, binascii, sys

file_xml = open("C:\mlp.xml", "r")
gluid = "3c7675febad2a6448446f083cb4a837f7d52d3fdabf16a38afeed478865b17db128669a6"
gluid_hex = gluid.decode("hex")
SIZE_ADJUSTMENT = 24
INNER_KEY = "302a75507acbd72f89504e4712901cf0"

try:
    xml_data = file_xml.read()
finally:
    file_xml.close()

encry_xml = xxtea.encrypt(xml_data, binascii.unhexlify(INNER_KEY), False)
comp_data = zlib.compress(encry_xml)
crc = struct.pack('i', zlib.crc32(encry_xml))
encry_data = xxtea.encrypt(comp_data + crc, gluid_hex, False)
  
uncomp_size = struct.pack('i', sys.getsizeof(xml_data) + 3 - SIZE_ADJUSTMENT)
comp_size = struct.pack('i', sys.getsizeof(comp_data) + 4 - SIZE_ADJUSTMENT)
encry_size = struct.pack('i', sys.getsizeof(encry_data) - SIZE_ADJUSTMENT)
reserved = '\x01\x00\x00\x00'

file_save = open("C:\mlp_save_new.dat", "wb")
try:
    file_save.write(uncomp_size)
    file_save.write(comp_size)
    file_save.write(encry_size)
    file_save.write(encry_data)
    file_save.write(reserved)
finally:
    file_save.close()
