#!usr/bin/env python3
from pathlib import Path
modules = Path("input.txt").read_text().splitlines()
# print(sum(map(lambda m: (int(m) // 3) - 2, modules)))
print(sum([ (int(m) // 3) - 2 for m in modules ]))
