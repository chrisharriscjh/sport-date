package sportdate

import Skeleton.{IsSportDate, IsDateRange}
import IsSportDateInstances._
import IsSportDateSyntax._

object TimeSeriesTypes {

  case class BDateRange[DateT] (
    start: DateT,
    numDates: Option[Integer],
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
    def fromStartEnd(start: DateT, end: DateT): BDateRange = 
      BDateRange(start, None, Some(end))
    def fromStartNum(start: DateT, numDates: Integer): BDateRange = 
      BDateRange(start, Some(numDates), None)
    def fromStartNumEnd(start: DateT, numDates: Integer, end: DateT): BDateRange = 
      BDateRange(start, Some(numDates), Some(end))
  }

  //case class PriceTs (
    //dates: List[Date],
    //prices: List[Price],
  //) extends Skeleton.IsSingleTs[Price] {
    //def atDate(d: Date): Option[Price] = dates.indexOf(d) match {
      //case i if i >= 0 => Some(prices(i))
      //case _ => None
    //}
  //}

  //object PriceTs {
    //def fromDatesPrices(
      //ds: List[Date], ps: List[Price],
    //): Either[ErrorsObj.DateAndListLengthsDoNotMatch[Price] , PriceTs] = 
      //if (ds.length == ps.length) {
        //Right(PriceTs(ds, ps))
      //} else {
        //Left(ErrorsObj.DateAndListLengthsDoNotMatch(ds, ps))
      //}
  //}

  //case class HoldingsTs (
    //dateRange: BDateRange,
    //holdings: List[List[Position]],
  //) extends Skeleton.IsMultiTs[Position] {
    //def atDate(d: Date): List[Position] = ???
    //def addDelta(de: Skeleton.IsDelta[Position]): HoldingsTs = ???
    //def headTail: (List[Position], 
  //}
}

