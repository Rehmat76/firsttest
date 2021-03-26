package com.app.firsttask.utils.general

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AppConstants {

    companion object {
        var dateFormat: SimpleDateFormat = SimpleDateFormat("dd-mm-yyyy")
        var dateTimeFormat: SimpleDateFormat = SimpleDateFormat("dd-mm-yyyy hh:mm:ss")
        var formatDate = SimpleDateFormat("hh:mm a")
        val BASE_URL = "https://www.breakingbadapi.com/api/"

        // User List PageNumber
        const val STARTING_PAGE_LIMIT = 10
        const val STARTING_PAGE_OFFSET = 0

        fun getAge(date: String?): Int {
            var age = 0
            try {
                val date1: Date = dateFormat.parse(date)
                val now: Calendar = Calendar.getInstance()
                val dob: Calendar = Calendar.getInstance()
                dob.time = date1
                require(!dob.after(now)) { "Can't be born in the future" }
                val year1: Int = now.get(Calendar.YEAR)
                val year2: Int = dob.get(Calendar.YEAR)
                age = year1 - year2
                val month1: Int = now.get(Calendar.MONTH)
                val month2: Int = dob.get(Calendar.MONTH)
                if (month2 > month1) {
                    age--
                } else if (month1 == month2) {
                    val day1: Int = now.get(Calendar.DAY_OF_MONTH)
                    val day2: Int = dob.get(Calendar.DAY_OF_MONTH)
                    if (day2 > day1) {
                        age--
                    }
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return age
        }

        fun getTime(dateTime: Date): String{
            return formatDate.format(dateTime)
        }

        fun printDifference(startDate: String): String {
            //milliseconds
            var age = 0
            val date1: Date = dateTimeFormat.parse("$startDate 00:00:00")
            var different = Date().time - date1.time
            val now: Calendar = Calendar.getInstance()
            val dob: Calendar = Calendar.getInstance()
            dob.time = date1
            require(!dob.after(now)) { "Can't be born in the future" }
            val year1: Int = now.get(Calendar.YEAR)
            val year2: Int = dob.get(Calendar.YEAR)
            age = year1 - year2
            val month1: Int = now.get(Calendar.MONTH)
            val month2: Int = dob.get(Calendar.MONTH)
            if (month2 > month1) {
                age--
            } else if (month1 == month2) {
                val day1: Int = now.get(Calendar.DAY_OF_MONTH)
                val day2: Int = dob.get(Calendar.DAY_OF_MONTH)
                if (day2 > day1) {
                    age--
                }
            }
            val secondsInMilli: Long = 1000
            val minutesInMilli = secondsInMilli * 60
            val hoursInMilli = minutesInMilli * 60
            val daysInMilli = hoursInMilli * 24
            val elapsedDays = different / daysInMilli
            different %= daysInMilli
            val elapsedHours = different / hoursInMilli
            different %= hoursInMilli
            val elapsedMinutes = different / minutesInMilli
            different %= minutesInMilli
            val elapsedSeconds = different / secondsInMilli
            System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
            )
            return "$age age $elapsedHours h $elapsedMinutes m"
        }
    }
}