import os
import re
import sys

## Open our files
f_in = open('E:\\Games\\Perplex City\\genetic.txt','r')
ref_in = open('E:\\Games\\Perplex City\\genetic_ref.csv','r')

## Read in our dictionary info
ref = {}
for line in ref_in:
    line = re.sub("\n", "", line)
    parts = line.split(",")
    ref[parts[1]] = parts[0]

## Read in the genetic code
genetic = ""
for line in f_in:
    genetic = genetic + line

## Close our files
f_in.close()
ref_in.close()

genetic = re.sub(" ", "", genetic)
genetic = re.sub("\n", "", genetic)

print("Length cipher text - " + str(len(genetic)))
print("Original string:")
print(genetic)
print

## Replace codons with amino acid letter
genetic_marked = ""
for index in range(0, len(genetic), 3):
    codon = genetic[index:index + 3]
    genetic_marked = genetic_marked + ref[codon.upper()]

print("Modified string:")
print(genetic_marked)
print

print("Parts:")
genetic_split = genetic_marked.split(".")
print(genetic_split[1])
print(genetic_split[2])
print(genetic_split[3])