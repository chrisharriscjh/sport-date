package sportdate

trait IsSportDate[A] {
  def prevDay(value: A): A
  def prevBDay(value: A): A
  def nextDay(value: A): A
  def nextBDay(value: A): A
  def toBDay(value: A): A
}

trait IsDateRange[D] {
  def start: D
  def end: D
  def days: List[D]
  def bDays: List[D]
  def numDays: Int
  def numBDays: Int
}
