fuel = 0

with open("input.txt") as input:
   for module in input:
      fuel += (int(module) / 3) - 2

print fuel