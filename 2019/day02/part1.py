#!usr/bin/env python3
from sys import argv
from pathlib import Path

def get_at(iptr, offset):
    return program[program[iptr + offset]]

def set_at(iptr, offset, val):
    program[program[iptr + offset]] = val

# load
# program = list(map(lambda x: int(x), Path(argv[1]).read_text().split(','))
program = [ int(x) for x in Path(argv[1]).read_text().split(',') ]

if argv[1] == "input.txt":
    program[1] = 12
    program[2] = 2

print(0, '->', program)

# run
for iptr in range(0, len(program), 4):
    opcode = program[iptr]

    if opcode == 1:
        set_at(iptr, 3, get_at(iptr, 1) + get_at(iptr, 2))
    elif opcode == 2:
        set_at(iptr, 3, get_at(iptr, 1) * get_at(iptr, 2))
    elif opcode == 99:
        break

    print(opcode, '->', program)
