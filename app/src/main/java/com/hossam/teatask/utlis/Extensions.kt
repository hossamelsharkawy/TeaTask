package com.hossam.teatask.utlis

import java.text.SimpleDateFormat
import java.util.Date

fun Long.timestampToTime(): String {
    val dateFormat = SimpleDateFormat("mm:ss")
    val date = Date(this)
    return dateFormat.format(date)
}