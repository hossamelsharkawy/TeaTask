package com.hossam.teatask.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Date


class TrackingTypeA(
    val timelineList: List<Status> = emptyList(),
    val icon: String,
    ) {
    data class Status(
        override val id: Int,
        override val name: String,
        override var time: Long = 0L,
        override var isCompleted: Boolean = false,
        val icon: ImageVector = Icons.Outlined.Star,
    ) : TrackingStatus {
        override fun toString() = "$name - $time\n "

        override fun update(): Status = copy(
            time = System.currentTimeMillis(),
            isCompleted = true
        )

        fun getDateReadable() =
            if (isCompleted) Date(time).toString().substringBeforeLast("GMT") else ""
    }
}