package com.example.weatherappdemo.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DateTimeUtils {

    const val DEFAULT_FORMAT = "hh:mm a"

    /*
    * Return time from millis
    */
    fun getTimeFromMillis(seconds: Long?): String? {
        return if (seconds == null || seconds == 0L) null else SimpleDateFormat(DEFAULT_FORMAT).format(seconds * 1000)
    }

    /*
    * Return Day from millis, like Monday/Tuesday etc.
    */
    fun getDayOfWeekFromMillis(millis: Long): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = Date(millis * 1000)
        return sdf.format(date)
    }

}