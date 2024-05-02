package com.hossam.teatask.data.models

import androidx.compose.runtime.Composable
import com.hossam.teatask.ui.order.OrderDetailsTypeAUI
import com.hossam.teatask.ui.order.OrderDetailsTypeBUI


interface TrackingStatus {
    val id: Int
    val name: String
    var time: Long
    var isCompleted: Boolean
    fun update(): TrackingStatus
}

interface StatusEmitterStrategy {
    fun getStatusList(): List<TrackingStatus>
}

class TrackingTypeAStatusEmitter : StatusEmitterStrategy {
    override fun getStatusList(): List<TrackingStatus> = listOf(
        TrackingTypeA.Status(
            id = 1,
            name = "Order received",
        ),
        TrackingTypeA.Status(
            id = 2,
            name = "On the way",
        ),
        TrackingTypeA.Status(
            id = 3,
            name = "Delivered",
        )
    )
}

class TrackingTypeBStatusEmitter : StatusEmitterStrategy {
    override fun getStatusList(): List<TrackingStatus> = listOf(
        TrackingTypeB.Status(
            id = 1,
            name = "Your Order has been placed",
        ),
        TrackingTypeB.Status(
            id = 2,
            name = "Your Order is on the way",
        ),
        TrackingTypeB.Status(
            id = 3,
            name = "Your Order has been delivered",
        )
    )
}

interface OrderUIStrategy {
    @Composable
    fun DrawUI(order: OrderDetailsDto)
}

class OrderUIStrategyTypeA : OrderUIStrategy {

    @Composable
    override fun DrawUI(order: OrderDetailsDto) {
        OrderDetailsTypeAUI(order = order as OrderDetailsTypeADto)
    }

}

class OrderUIStrategyTypeB : OrderUIStrategy {

    @Composable
    override fun DrawUI(order: OrderDetailsDto) {
        OrderDetailsTypeBUI(order = order as OrderDetailsTypeBDto)
    }

}
