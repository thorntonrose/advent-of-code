#!usr/bin/env python3
from sys import argv
from pathlib import Path

def set_at(offset, val): program[program[offset]] = val
def get_at(offset): return program[program[offset]]

# load program

program = list(map(lambda x: int(x), Path(argv[1]).read_text().split(',')))

if argv[1] == "input.txt":
    program[1] = 12
    program[2] = 2

print(0, '->', program)

# run program
for iptr in range(0, len(program), 4):
    opcode = program[iptr]

    if opcode == 1:
        set_at(iptr + 3, get_at(iptr + 1) + get_at(iptr + 2))
    elif opcode == 2:
        set_at(iptr + 3, get_at(iptr + 1) * get_at(iptr + 2))
    elif opcode == 99:
        break

    print(opcode, '->', program)
