def diff(list1, list2) {
   list1.withIndex().inject([]) { acc, tuple -> tuple[0] != list2[tuple[1]] ? acc + tuple[1] : acc }
}

// def ids = [ "abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz" ]
def ids = new File("input.txt").readLines()
ids = ids*.collect()

def id2
def id1 = ids.find { a -> id2 = ids.find { diff(a, it).size() == 1 } }
println id1
println id2

id1.removeAt diff(id1, id2)[0]
println id1.join("")