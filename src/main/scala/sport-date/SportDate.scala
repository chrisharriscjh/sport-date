package sportdate

import com.github.nscala_time.time.Imports._

abstract class IsSportDate[A] extends IsSportDateBase[A] {self =>
  def isBDay(value: A): Boolean
  def advanceDays(value: A, numDays: Int): A
  def prevBDay(value: A): A = advanceDays(value, -1) match {
    case d if isBDay(d) => d
    case d => prevBDay(d)
  }
  def nextBDay(value: A): A = advanceDays(value, 1) match {
    case d if isBDay(d) => d
    case d => nextBDay(d)
  }
  def toBDay(value: A): A = value match {
    case d if isBDay(d) => d
    case d => nextBDay(d)
  }
}

object IsSportDateInstances {
  implicit val dateTimeIsSportDate: IsSportDate[DateTime] = 
    new IsSportDate[DateTime] {
      def isBDay(value: DateTime): Boolean = (value.getDayOfWeek <= 5)
      def advanceDays(value: DateTime, numDays: Int): DateTime = value + numDays.days
    }
}

object IsSportDateSyntax {
  implicit class IsSportDateOps[A](value: A) {
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
