symbols = ['']
sequence = (('0', '0'), ('1', '1'), ('0', '1'), ('1', '0'), ('2', '0'), ('2', '1'), ('4', '1'), ('4', '0'), ('3', '1'), ('8', '0'), ('7', '1'), ('7', '0'), 
('11', '0'), ('10', '0'), ('9', '0'), ('14', '1'), ('3', '0'), ('12', '0'), ('15', '0'), ('10', '1'), ('17', '0'), ('5', '0'), ('19', '0'),
('21', '1'), ('24', '0'), ('13', '0'), ('21', '0'), ('19', '1'), ('8', '1'), ('27', '1'), ('26', '1'), ('29', '1'), ('14', '0'), ('15', '1'), 
('20', '1'), ('18', '0'), ('28', '1'), ('31', '0'), ('31', '1'), ('26', '0'), ('32', '0'), ('6', '0'), ('42', '0'), ('22', '1'), ('17', '1'), 
('35', '0'), ('18', '1'), ('45', '1'), ('41', '0'), ('44', '1'), ('5', '1'), ('40', '1'), ('13', '1'), ('44', '1'), ('36', '1'), ('48', '0'), 
('38', '0'), ('42', '1'), ('30', '1'), ('33', '1'), ('45', '0'), ('24', '1'), ('60', '1'), ('51', '0'), ('43', '0'), ('62', '1'), ('16', '1'), 
('63', '1'), ('67', '0'), ('22', '0'), ('34', '1'), ('62', '0'), ('70', '1'), ('48', '1'), ('52', '0'), ('6', '1'), ('69', '0'), ('54', '0'), 
('47', '1'), ('63', '0'), ('77', '0'), ('72', '0'), ('46', '0'), ('59', '1'), ('79', '0'), ('73', '1'), ('25', '0'), ('23', '0'), ('11', '1'), 
('85', '0'), ('78', '0'), ('91', '0'), ('49', '0'), ('25', '1'), ('46', '1'), ('47', '0'), ('23', '1'), ('61', '1'), ('29', '0'), ('24', '-'))

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
    value = curItem[1].replace('-','')
    
    value = symbols[index] + value
    print(str(index) + " " + value)
        
    symbols.append(value)
    answerString = answerString + value
    counter = counter + 1
    
# Print some statistics    
#Enumchar = len(answerString);
#print("Answer:" + answerString)
#print("\nLength: {0}".format(numchar))
#for i in range(1, 9):
#    print("Div {0}: {1}".format(i, numchar/i))
    
section(8)
print(answerChars)

plaintext = ""
for char in answerChars:
    newint = int(char, 2)
    #print(str(newint) + " " + chr(newint))
    plaintext = plaintext + chr(newint)

print(plaintext)
print(len(plaintext)-5)