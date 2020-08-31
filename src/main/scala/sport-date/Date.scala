package sportdate

import com.github.nscala_time.time.Imports._
import Skeleton.IsSportDate

object IsSportDateInstances {
  implicit val nScalaTimeDate: IsSportDate[DateTime] = 
    new IsSportDate[DateTime] {
      def nextDay(d: DateTime): DateTime = d + 1.days
      def nextBDay(d: DateTime): DateTime  = nextDay(d) match {
        case d if d.getDayOfWeek > 5 => nextDay(d)
        case d => d
      }
      def toBDay(d: DateTime): DateTime = d match {
        case d if d.getDayOfWeek > 5 => nextDay(d)
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
