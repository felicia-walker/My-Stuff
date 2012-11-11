PIECE_LEN = 5

for c in range(1,20):
    for r in range(1,20):
        h = PIECE_LEN * r
        w = PIECE_LEN * c + 2
        area = h * w

        if (area % 6 == 0):
            total = c * r
            print("{0} across by {1} high = {2} total".format(c, r, total))
