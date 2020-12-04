#!usr/bin/env python3
from sys import argv
from pathlib import Path

def get_at(program, iptr, offset):
    return program[program[iptr + offset]]

def set_at(program, iptr, offset, val):
    program[program[iptr + offset]] = val

def load(intcode, x, y):
    program = intcode.copy()
    program[1] = x
    program[2] = y

    print(program)
    return program

def run(program):
    for iptr in range(0, len(program), 4):
        if program[iptr] == 1:
            set_at(program, iptr, 3, get_at(program, iptr, 1) + get_at(program, iptr, 2))
        elif program[iptr] == 2:
            set_at(program, iptr, 3, get_at(program, iptr, 1) * get_at(program, iptr, 2))
        elif program[iptr] == 99:
            break

    print(x, y, program[0])
    return program

# load intcode
# intcode = list(map(lambda x: int(x), Path(argv[1]).read_text().split(',')))
intcode = [ int(x) for x in Path(argv[1]).read_text().split(',') ]

for x in range(100):
    for y in range(100):
        if run(load(intcode, x, y))[0] == 19690720: exit()
