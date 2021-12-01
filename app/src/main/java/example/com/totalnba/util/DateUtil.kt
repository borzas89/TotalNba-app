package example.com.totalnba.util

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*

object DateUtil {

    private val DATE_FULL_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    private val SHORT_DATE_FORMAT = DateTimeFormat.forPattern("MM dd.")

    fun formatShortDate(date: String) =
            format(date, SHORT_DATE_FORMAT)

    private fun format(date: String, formatter: DateTimeFormatter): String {
        return LocalDate.parse(date, DATE_FULL_FORMAT).toDate().toString()
    }

}