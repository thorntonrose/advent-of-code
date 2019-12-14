#!usr/bin/env python3
from sys import argv
from pathlib import Path

def set_at(offset, val): program[program[offset]] = val
def get_at(offset): return program[program[offset]]

# load intcode
intcode = list(map(lambda x: int(x), Path(argv[1]).read_text().split(',')))

for x in range(100):
    for y in range(100):
        # load program
        program = intcode.copy()
        program[1] = x
        program[2] = y
        print(program)

        # run program
        for iptr in range(0, len(program), 4):
            opcode = program[iptr]

            if opcode == 1:
                set_at(iptr + 3, get_at(iptr + 1) + get_at(iptr + 2))
            elif opcode == 2:
                set_at(iptr + 3, get_at(iptr + 1) * get_at(iptr + 2))
            elif opcode == 99:
                break

        print(x, y, program[0])
        if program[0] == 19690720: exit()
