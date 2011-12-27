max_h = 0
amounts = [0, 0, 0]

for m in range (1, 41):
    for g in range (1, 9):
        for s in range (1, 401):
            total = 10*m + 50*g + s
            if (total <= 800):
                h = 10 * pow(m, 0.9) * pow(s, 0.2) * pow(g, 1.6)

                if (h > max_h):
                    max_h = h
                    amounts = [m, s, g]

print("Max h is {0}".format(max_h))
print("M = {0[0]}, S = {0[1]}, G = {0[2]}".format(amounts))
