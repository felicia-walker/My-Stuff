# Magic Cube
#
# This was a work in progress to help figure out a magic cube that possible used
# 42 as the magic number. I was trying to see what combinations of three numbers
# would add to this.

combos = []

for i in range (1, 28):
    for j in range (1, 28):
        if (i != j):
            for k in range (1, 28):
                if (i != k) and (j != k) and (i + j + k == 42):
                    combos.append((i, j, k))

for combo in combos:
    print(combo)
