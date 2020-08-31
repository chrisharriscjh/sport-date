package sportdate

import Skeleton.{IsSportDate, IsDateRange}
import IsSportDateInstances._
import IsSportDateSyntax._

case class BDateRange[DateT] (
  start: DateT,
  numDates: Option[Int],
  end: Option[DateT],
)(implicit sportDateTc: IsSportDate[DateT]) extends IsDateRange[DateT] {
  lazy val toList: List[DateT] = this match {
    case BDateRange(s, None, Some(e)) => 
      if(s == e) {List(s)} else {
        val nxt = s.toBDay
        nxt :: BDateRange(nxt.nextBDay, None, Some(e)).toList
      }  
    case BDateRange(s, Some(n), None) => 
      if(n <= 1) {List(s)} else {
        val nxt = s.toBDay
        nxt :: BDateRange(nxt.nextBDay, Some(n), None).toList
      }
    case BDateRange(s, Some(n), Some(e)) => 
      if(s == e || n <= 1) {List(s)} else {
        val nxt = s.toBDay
        nxt :: BDateRange(nxt.nextBDay, Some(n), None).toList
      }
    case _ => throw new Exception("BDateRange has no end condition")
  }
  lazy val length: Int = this.toList.length
}

object BDateRange {
  def fromStartEnd[DateT](start: DateT, end: DateT)(
    implicit tc: IsSportDate[DateT]) = 
    BDateRange(start, None, Some(end))
  def fromStartNum[DateT](start: DateT, numDates: Int)(
    implicit tc: IsSportDate[DateT]) = 
    BDateRange(start, Some(numDates), None)
  def fromStartNumEnd[DateT](start: DateT, numDates: Int, end: DateT)(
    implicit tc: IsSportDate[DateT]) = 
    BDateRange(start, Some(numDates), Some(end))
}

