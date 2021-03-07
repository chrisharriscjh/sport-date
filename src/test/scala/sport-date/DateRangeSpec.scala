package sportdate

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import IsSportDateInstances._
import IsSportDateSyntax._

class DateRange extends AnyFlatSpec with Matchers {
  "BDateRange" should "instantiate from a start and end date" in {
    val act = BDateRange.fromStartEnd(start=SportDate.YMD(2020,8,1), end=SportDate.YMD(2020,8,10)).toList
    val exp = List(
      SportDate.YMD(2020,8,3), 
      SportDate.YMD(2020,8,4), 
      SportDate.YMD(2020,8,5), 
      SportDate.YMD(2020,8,6), 
      SportDate.YMD(2020,8,7), 
      SportDate.YMD(2020,8,10)
    ) 
    assert(act == exp)
  }
}
