def calc_fuel(module):
   fuel = (int(module) / 3) - 2
   # print fuel
   return 0 if fuel <= 0 else fuel + calc_fuel(fuel)

total_fuel = 0

with open("input.txt") as input:
   for module in input:
      total_fuel += calc_fuel(module)

print total_fuel