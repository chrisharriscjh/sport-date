package sportdate

import com.github.nscala_time.time.Imports._

object IsSportDateInstances {
  implicit val dateTimeIsSportDate: IsSportDate[DateTime] = 
    new IsSportDate[DateTime] {
      def prevDay(d: DateTime): DateTime = d - 1.days
      def prevBDay(d: DateTime): DateTime = prevDay(d) match {
        case d if d.getDayOfWeek > 5 => prevBDay(d)
        case d => d
      }
      def nextDay(d: DateTime): DateTime = d + 1.days
      def nextBDay(d: DateTime): DateTime  = nextDay(d) match {
        case d if d.getDayOfWeek > 5 => nextBDay(d)
        case d => d
      }
      def toBDay(d: DateTime): DateTime = d match {
        case d if d.getDayOfWeek > 5 => nextBDay(d)
        case d => d
      }
    }
}

object IsSportDateSyntax {
  implicit class IsSportDateOps[A](value: A) {
    def nextDay(implicit dateTCInstance: IsSportDate[A]): A = 
      dateTCInstance.nextDay(value)
    def nextBDay(implicit dateTCInstance: IsSportDate[A]): A = 
      dateTCInstance.nextBDay(value)
    def toBDay(implicit dateTCInstance: IsSportDate[A]): A = 
      dateTCInstance.toBDay(value)
  }
}

object SportDate {
  def YMD(y: Integer, m: Integer, d: Integer): DateTime =
    (new DateTime)
      .withYear(y).withMonthOfYear(m).withDayOfMonth(d)
      .withTimeAtStartOfDay()
}
