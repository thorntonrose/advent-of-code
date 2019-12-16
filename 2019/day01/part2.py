#!usr/bin/env python3
from pathlib import Path

def calc_fuel(module):
   fuel = (int(module) // 3) - 2
   return 0 if fuel <= 0 else fuel + calc_fuel(fuel)

modules = Path("input.txt").read_text().splitlines()
# print(sum(map(lambda m: calc_fuel(m), modules)))
print(sum(calc_fuel(m) for m in modules))
