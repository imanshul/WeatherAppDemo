package com.example.weatherappdemo

import com.example.weatherappdemo.utils.DateTimeUtils
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

/*
*Created By Anshul Thakur on 2/25/2020.
*/

class DateTimeUtilsTest {
    @Test
    fun check_getTimeFromMillis_ForNullValue_ReturnNull(){
        assertNull(DateTimeUtils.getTimeFromMillis(null))
    }

    @Test
    fun check_getTimeFromMillis_ForZeroValue_ReturnsNull(){
        assertNull(DateTimeUtils.getTimeFromMillis(0L))
    }

    @Test
    fun check_getTimeFromMillis_ForCorrectValue_ReturnTime(){
        assertEquals("10:58 PM",DateTimeUtils.getTimeFromMillis(1582651723))
    }

    @Test
    fun check_dayOfWeek_ForZeroValue_ReturnTrue(){
        assertEquals("Thursday",DateTimeUtils.getDayOfWeekFromMillis(0L))
    }

    @Test
    fun check_dayOfWeek_ForCorrectValue_ReturnTrue(){
        assertEquals("Tuesday",DateTimeUtils.getDayOfWeekFromMillis(1582651723))
    }
}