package com.hossam.teatask.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.ui.graphics.Color

class TrackingTypeB(
    val timelineList: List<Status> = emptyList(),
    val delivery: Delivery,
    val icon: String,
) {
    class Delivery(val address: String)

    data class Status(
        override val id: Int,
        override val name: String,
        override var time: Long = 0,
        override var isCompleted: Boolean = false,
        var color: Color = Color.Unspecified,
    ) : TrackingStatus {
        override fun toString() = "$name - $isCompleted\n"
        override fun update(): Status = copy(
            time = System.currentTimeMillis(),
            isCompleted = true
        )

        fun getCurrentColor() = if (isCompleted) color else Color.Unspecified
        fun getCurrentIcon() =
            if (isCompleted) Icons.Filled.CheckCircle else Icons.Outlined.Lock
    }

}

