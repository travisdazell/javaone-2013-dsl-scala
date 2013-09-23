package net.travisdazell.javaone.golf.model

import java.util.Date

class TeeTime(time: Date, golfer: Golfer) {
  def schedule(): String = {
    val xml =
      <tee-time>
        <time>{ time }</time>
        <golfer>
          <first-name>{ golfer.firstName.replaceAll("\"", "") }</first-name>
          <last-name>{ golfer.lastName.replaceAll("\"", "") }</last-name>
          <guests>{ golfer.numberOfGuests }</guests>
        </golfer>
      </tee-time>

    xml.toString()
  }
}