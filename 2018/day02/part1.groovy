def letterFreq(id) {
   id.inject([:]) { map, letter ->
      map[letter] = (map[letter] ?: 0) + 1
      map
   }
}

def countFreq(ids, num) {
   ids.count { k, v -> num in v.values() }
}

// lines = [ "abcd", "abad", "aaab" ]
lines = new File("input.txt").readLines()

ids = lines.inject([:]) { map, id ->
   map[id] = letterFreq(id)
   map
}

checksum = countFreq(ids, 2) * countFreq(ids, 3)
println checksum