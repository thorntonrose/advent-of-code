def toClaim(text) {
   def tokens = text.tokenize(" ")
   def (x, y) = tokens[2].tokenize(",")
   def (h, w) = tokens[3].tokenize("x")
   [ id: tokens[0][1..-1], x: x as int, y: y[0..-2] as int, h: h as int, w: w as int ]
}

def mark(squares, claim) {
   claim.h.times { m ->
      claim.w.times { n ->
         def square = ((claim.x + m) * 1000) + claim.y + n
         squares[square] = (squares[square] ?: []) + [ claim.id ]
      }
   }
}

// input = [ "#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2" ]
input = new File("input.txt").readLines()
def squares = [:]
def claims = []

input.each {
   def claim = toClaim(it)
   claims << claim
   mark squares, claim
}

println claims.find { claim ->
   def count = 0

   claim.h.times { m ->
      claim.w.times { n ->
         def square = ((claim.x + m) * 1000) + claim.y + n
         count += (squares[square].size() == 1 ? 1 : 0)
      }
   }

   count == claim.h * claim.w
}
