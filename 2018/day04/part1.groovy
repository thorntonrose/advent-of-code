def toEvent(line, id) {
   def tokens = line.tokenize(" ")
   def time = tokens[1][0..-2]
   def event = [ date: tokens[0][6..-1], start: time, minute: time.tokenize(":")[1] as int ]

   switch(tokens[2]) {
      case "Guard":
         event += [ type: "begin", id: tokens[3][1..-1] ]
         break
      case "falls":
         event += [ type: "sleep", id: id ]
         break
      default:
         event += [ type: "wake", id: id ]
   }

   event
}

log = """[1518-11-01 00:00] Guard #10 begins shift
[1518-11-01 00:05] falls asleep
[1518-11-01 00:25] wakes up
[1518-11-01 00:30] falls asleep
[1518-11-01 00:55] wakes up
[1518-11-01 23:58] Guard #99 begins shift
[1518-11-02 00:40] falls asleep
[1518-11-02 00:50] wakes up
[1518-11-03 00:05] Guard #10 begins shift
[1518-11-03 00:24] falls asleep
[1518-11-03 00:29] wakes up
[1518-11-04 00:02] Guard #99 begins shift
[1518-11-04 00:36] falls asleep
[1518-11-04 00:46] wakes up
[1518-11-05 00:03] Guard #99 begins shift
[1518-11-05 00:45] falls asleep
[1518-11-05 00:55] wakes up""".readLines()

id = 0
sleepEvent = null

events = log.inject([]) { list, line ->
   def event = toEvent(line, id)

   switch (event.type) {
      case "begin":
         id = event.id
         break
      case "sleep":
         sleepEvent = event
         break
      default:
         (event.minute - sleepEvent.minute - 1).times { n ->
            sleepEvent = sleepEvent.inject([:]) { m, k, v -> m[k] = v; m }
            sleepEvent.minute ++
            list << sleepEvent
         }
   }

   list << event
}

events.each { println it }