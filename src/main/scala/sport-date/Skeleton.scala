package sportdate

object Skeleton {

  trait IsSportDate[A] {
    def nextDay(value: A): A
    def nextBDay(value: A): A
    def toBDay(value: A): A
  }

  trait IsDateRange[DateT] {
    def toList: List[DateT]
    def length: Int
    def fromStartEnd(start: DateT, end: DateT): IsDateRange[DateT]
  }
}
