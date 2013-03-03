# My Little Pony: Friendship Is Magic save file decryption utility
#
# This program will fully decrypt an MLP save file into XML for your editing pleasure.
# Right now you have to put the filenames and GLUIDs into this program. I'll clean that up
# at some point to make it more user friendly.
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
# 2. The XML lines you are probably most interested in tweaking are:
#
#    <PlayerData Level="60" XP="121950" Coins="10000000" Hearts="9999" Social="163" LottoCurrency="1" Spirit="0" RaceHighScore="155" LottoTicketTimer="600" Language="0" SessionCount="339" LottoPlayed="1" BitsSpentTotal="2581580" GemSpentTotal="158" BronzePlayed="85" SilverPlayed="3" GoldPlayed="4" TimeBallbounce="7569" TimeMagicbook="0" TimeApple="9208" TimeRacing="3681" TimeFunfair="1108" BitsSpentDaily="2446165" GemSpentDaily="142" QuestsComplete="39" ItemsProduced="" FirstSNPopupComplete="0" FirstIAPPopupComplete="0">
#    <Shards Loyalty="148" Kindness="265" Honesty="205" Generosity="204" Laughter="108" Magic="500" />
# 
#    Not confirmed, but 9999 may be the max gems your can give yourself.  Weird they are under "hearts".
# 3. You may be able to remove clearable object by removing sections similar to:
#
#    <Object ID="Clearable_Rock01 Huge">
#    <Position x="30" y="128" />
#    </Object>
#
# 4. You can give yourself Gameloft trophies in this line:
#
#    <Trophies PonyNews="0" ReadAllAboutIt="0" Chatterbox="0" SharingKindness="0" GettingFriendly="1" GenerousBuddy="1" CharitablePal="0" BFFs="0" MakingFriends="1" MostValuablePony="1" FriendshipIsMagic="0" GiftPony="1" PinTheTail="1" WhatAGem="1" GemmingUpTheWorks="1" DragonFeeder="0" MovingParty="1" />

import struct, xxtea, zlib, binascii

file_old = open("C:\mlp_save.dat", "rb")
gluid = "3c7675febad2a6448446f083cb4a837f7d52d3fdabf16a38afeed478865b17db128669a6"
gluid_hex = gluid.decode("hex")
WORD_SIZE = 4
INNER_KEY = "302a75507acbd72f89504e4712901cf0"

try:
    data = file_old.read()
finally:
    file_old.close()

data_size = len(data)  
uncomp_size = struct.unpack('i', data[0:WORD_SIZE])
comp_size = struct.unpack('i', data[WORD_SIZE:2 * WORD_SIZE])
encry_size = struct.unpack('i', data[2 * WORD_SIZE:3 * WORD_SIZE])
encry_data = data[3 * WORD_SIZE:data_size - WORD_SIZE]
reserved = struct.unpack('i', data[data_size - WORD_SIZE:len(data)])

decry_data = xxtea.decrypt(encry_data, gluid_hex, False)
uncomp_data = zlib.decompress(decry_data)
xml_data = xxtea.decrypt(uncomp_data, binascii.unhexlify(INNER_KEY), False)

file_xml = open("C:\mlp.xml", "w")
try:
    file_xml.write(xml_data)
finally:
    file_xml.close()
