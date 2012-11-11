symbols = ['100', '101']
sequence = (('1', '2'), ('0', '2'), ('0', '0'), ('1', '0'), ('1', '1'), ('2', '2'), 
            ('2', '1'), ('2', '0'), ('4', '2'), ('3', '2'), ('3', '0'), ('3', '1'), 
            ('4', '1'), ('6', '1'), ('5', '1'), ('5', '2'), ('6', '2'), ('6', '0'), 
            ('5', '0'), ('5', '1'), ('8', '0'), ('8', '2'), ('7', '2'), ('7', '1'), 
            ('8', '1'), ('10', '0'), ('10', '2'), ('9', '2'), ('9', '0'), ('9', '1'), 
            ('10', '1'))

def section(size):
    for i in range(0, len(answerString), size):
        char = answerString[i:i+size]
        answerChars.append(char)

# Process the sequence
answerString = ""
answerChars = []

counter = 0
for curItem in sequence:
    index = int(curItem[0])
    value = curItem[1]
    
    #if index != counter:
    value = symbols[index] + value
        
    #if value not in symbols:
    symbols.append(value)
    answerString = answerString + value
    counter = counter + 1
    
section(3)
print(answerChars)
print(symbols)

plaintext = ""
nums = []
for char in answerChars:
    #char = char[::-1]
    newint = int(char, 3)
    nums.append(newint)
    #print(str(newint) + " " + chr(newint + ord('a')))
    plaintext = plaintext + chr(newint + ord('a') - 1)

print(plaintext)
print(len(plaintext)-5)
print(nums)