package sportdate

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.github.nscala_time.time.Imports._

import SportDate._
import IsSportDateSyntax._
import IsSportDateInstances._

class SportDateSpec extends AnyFlatSpec with Matchers {
  "nextBDay" should "advance one day if weekday" in {
    assert(SportDate.YMD(2020,8,3).nextBDay == SportDate.YMD(2020,8,4))
  }
  "nextBDay" should "advance to Monday if Saturday" in {
    assert(SportDate.YMD(2020,8,1).nextBDay == SportDate.YMD(2020,8,3))
  }
  "nextBDay" should "advance to Monday if Sunday" in {
    assert(SportDate.YMD(2020,8,2).nextBDay == SportDate.YMD(2020,8,3))
  }
  "toBDay" should "return the caller if a weekday" in {
    assert(SportDate.YMD(2020,8,3).toBDay == SportDate.YMD(2020,8,3))
  }
  "toBDay" should "return the next Business day if a weekend" in {
    assert(SportDate.YMD(2020,8,1).toBDay == SportDate.YMD(2020,8,3))
  }
}
