package net.travisdazell.javaone.golf.parsers

import net.travisdazell.javaone.golf.model.TeeTime
import scala.io.Source

object TeeTimeParserTester {
  def main(args: Array[String]) {
    val inputFile = Source.fromFile("scripts/test.golf")
    val inputSource = inputFile.mkString

    val parser = new TeeTimeParser
    val result = parser.parseAll(parser.teeTime, inputSource)
    val teeTime = result.get

    println(teeTime.schedule)
  }
}