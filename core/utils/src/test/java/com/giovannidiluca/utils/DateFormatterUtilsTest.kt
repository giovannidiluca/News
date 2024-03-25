package com.giovannidiluca.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DateFormatterUtilsTest {

    @Test
    fun transformDate_shouldReturnFormattedDate() {
        val inputDate = "2024-03-22T19:36:00Z"
        val expectedOutputDate = "Mar, 22 2024"

        val formattedDate = DateFormatterUtils.transformDate(inputDate)

        assertEquals(expectedOutputDate, formattedDate)
    }
}
