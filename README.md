## SportDate

A small but logical package for working with business-day-aware dates in Scala.

**Provides -**
- a typeclass `IsSportDate` for representing date that can be easily converted to business (week-day) dates
- a pre-defined instance of this typeclass, where Joda-time's DateTime object implements `IsSportDate`  
- an `IsDateRange` typeclass for representing ranges of dates
- a pre-defined instance of the `IsDateRange` typeclass, `BDateRange`.

```
import sportdate._
import IsSportDateSyntax._
import IsSportDateInstances._

// Creates a Joda-time DateTime instance by default
val aSaturday = SportDate.YMD(2021,3,6)

val aSunday = aSaturday.nextDay
println(aSunday) // 2021-03-07T00:00:00.000+09:00

val aMonday = aSaturday.nextBDay
println(aMonday) // 2021-03-08T00:00:00.000+09:00

// returns the current date if a business day; otherwise rolls forward to the next business day
println(aMonday.toBDay) // 2021-03-08T00:00:00.000+09:00
```

A range of business dates can be easily created - there are a few constructors:
```
val marchBDates = BDateRange.fromStartEnd(start=SportDate.YMD(2021,3,1), end=SportDate.YMD(2021,3,31))
println(marchBDates.length) // 23 

val twentyBDates = BDateRange.fromStartNum(start=SportDate.YMD(2021,3,1), numDates=20)
println(twentyBDates.end) 
```

