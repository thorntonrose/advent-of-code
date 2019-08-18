def toClaim(text) {
   def tokens = text.tokenize(" ")
   def (x, y) = tokens[2].tokenize(",")
   def (h, w) = tokens[3].tokenize("x")
   [ id: tokens[0][1..-1], x: x as int, y: y[0..-2] as int, h: h as int, w: w as int ]
}

def markClaims(claims) {
   // ???: This could be a map with MxN as keys and counts as values.
   def cloth = new List[1000][1000]

   claims.each { text ->
      def claim = toClaim(text)
      println claim

      claim.h.times { m ->
         claim.w.times { n ->
            def ids = cloth[claim.x + m][claim.y + n] ?: []
            cloth[claim.x + m][claim.y + n] = ids + [ claim.id ]
         }
      }
   }

   cloth
}

def calcOverlap(cloth) {
   def overlap = 0

   cloth.each { row ->
      def ids = row.findAll { it }

      if (ids) {
         println ids
         overlap += ids.count { it.size() > 1 }
      }
   }

   overlap
}

input = [ "#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2" ]
println calcOverlap(markClaims(input))