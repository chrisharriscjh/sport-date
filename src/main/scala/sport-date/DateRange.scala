package sportdate

import IsSportDateInstances._
import IsSportDateSyntax._

case class DateRange[D] (
  start: D,
  end: D) (implicit 
  e0: IsSportDate[D],
  e1: Ordering[D],
) extends IsDateRange[D] {
  lazy val days: List[D] = if(start == end) {List(start)} else {
    start :: DateRange(e0.advanceDays(start, 1), end).days
  }
  def bDays: List[D] = if(e1.gt(start,end)) {Nil} else {
    val nxt = start.toBDay
    nxt :: DateRange(nxt.nextBDay, end).bDays
  }
  def numDays: Int = days.length
  def numBDays: Int = bDays.length
}
object DateRange {
  def fromStartEnd[D](start: D, end: D) (implicit
    e0: IsSportDate[D],
    e1: Ordering[D],
  ): DateRange[D] = 
    DateRange[D](start, end)
}

//case class BasicDateRange[DateT] (
  //start: DateT,
  //end: DateT,
//) {
  //lazy val days: List[DateT] 
  //lazy val bDays: List[DateT] = this match {
    //case DateRange(s, e) => 
      //if(s == e) {List(s)} else {
        //val nxt = s.toBDay
        //nxt :: DateRange(nxt.nextBDay, None, Some(e)).toList
      //}  
    //case DateRange(s, n), None) => 
      //if(n <= 1) {List(s)} else {
        //val nxt = s.toBDay
        //nxt :: DateRange(nxt.nextBDay, Some(n), None).toList
      //}
    //case BDateRange(s, Some(n), Some(e)) => 
      //if(s == e || n <= 1) {List(s)} else {
        //val nxt = s.toBDay
        //nxt :: BDateRange(nxt.nextBDay, Some(n), None).toList
      //}
    //case _ => throw new Exception("BDateRange has no end condition")
  //}
  //lazy val length: Int = this.toList.length
  //lazy val numDays: Int

//}

//object DateRange {
  //def fromStartEnd[DateT](start: DateT, end: DateT)(
    //implicit tc: IsSportDate[DateT]) = 
    //DateRange(start, end)
  //def fromStartNumDays[DateT](start: DateT, numDays: Int)(
    //implicit tc: IsSportDate[DateT]) = 
    //DateRange(start, Some(length), None)
  ////def fromLengthEnd[DateT](length: Int
//}

