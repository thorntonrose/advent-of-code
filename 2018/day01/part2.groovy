def input = new File("input.txt").readLines().collect { it as int }
def freqs = [:]
def acc = 0

while (!input.find {
   acc += it
   freqs[acc] = (freqs[acc] ?: 0) + 1
   return freqs[acc] == 2
}) {}

println freqs.find { k, v -> v == 2 }.key