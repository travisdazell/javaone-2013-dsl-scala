package net.travisdazell.javaone.golf.parsers

import scala.util.parsing.combinator.RegexParsers
import net.travisdazell.javaone.golf.model.TeeTime
import net.travisdazell.javaone.golf.model.Golfer
import java.util.Date
import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import java.text.SimpleDateFormat

class TeeTimeParser extends JavaTokenParsers {
  val date = "\\d{2}-[a-zA-Z]{3}-\\d{4}\\s\\d{1,2}:\\d{2}\\s(AM|PM)".r

  def teeTime: Parser[TeeTime] = ("new tee time at" ~> time <~ "for") ~ golfer ^^ {
    case t ~ g => {
      new TeeTime(t, g)
    }
  }

  def time: Parser[Date] = date ^^ {
    d =>
      {
        val format = new java.text.SimpleDateFormat("d-MMM-yyyy hh:mm a")
        format.parse(d)
      }
  }

  def golfer: Parser[Golfer] = firstName ~ lastName ~ numberOfGuests ^^ {
    case f ~ l ~ n => {
      new Golfer(f, l, n)
    }
  }

  def firstName: Parser[String] = ident ^^ { name => name }

  def lastName: Parser[String] = ident ^^ { name => name }

  def numberOfGuests: Parser[Integer] = "and" ~> wholeNumber <~ "guests" ^^ { n => n.toInt }
}