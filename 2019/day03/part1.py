from operator import add
from sys import argv
from pathlib import Path

increments = { "L": (-1, 0), "R": (1, 0), "U": (0, 1), "D": (0, -1) }
port = (1, 1)
wires = [ [ port ], [ port ] ]

# plot points
for index, path in enumerate(Path(argv[1]).read_text().splitlines()):
    for token in path.split(","):
        direction = token[0]
        distance = int(token[1:])

        for n in range(distance):
            wires[index].append(tuple(map(add, wires[index][-1], increments[direction])))

    wires[index].remove(port)

# find intersections
intersections = list(set(wires[0]).intersection(wires[1]))
print(intersections)

# find shortest distance
min_distance = min(abs(p[0] - port[0]) + abs(p[1] - port[1]) for p in intersections)
print(min_distance)
