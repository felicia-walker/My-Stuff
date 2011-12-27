combos = []

if __name__ == '__main__':
    for i in range (1, 28):
        for j in range (1, 28):
            if (i != j):
                for k in range (1, 28):
                    if (i != k) and (j != k) and (i+j+k == 42):
                        combos.append((i,j,k))

for combo in combos:
    print(combo)