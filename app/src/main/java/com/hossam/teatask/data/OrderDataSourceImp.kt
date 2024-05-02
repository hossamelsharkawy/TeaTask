package com.hossam.teatask.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.twotone.Done
import androidx.compose.ui.graphics.Color
import com.hossam.teatask.data.models.ConfigurationData
import com.hossam.teatask.data.models.OrderDetailsDto
import com.hossam.teatask.data.models.OrderDetailsStrategyContext
import com.hossam.teatask.data.models.TrackingTypeA
import com.hossam.teatask.data.models.TrackingTypeB
import com.hossam.teatask.domain.OrderDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class OrderDataSourceImp @Inject constructor(
    orderDetailsStrategyContext:OrderDetailsStrategyContext
) : OrderDataSource {

    // todo expected as a json data from Endpoint
    val data = ConfigurationData(
        "Order Title",
        ConfigurationData.Tracking(
            ConfigurationData.Tracking.Vertical,
            trackingTypeA = TrackingTypeA(
                timelineList = listOf(
                    TrackingTypeA.Status(
                        id = 1,
                        name = "Order received",
                        icon = Icons.Filled.ShoppingCart,
                    ), TrackingTypeA.Status(
                        id = 2,
                        name = "On the way",
                        icon = Icons.Filled.LocationOn,
                    ), TrackingTypeA.Status(
                        name = "Delivered",
                        id = 3,
                        icon = Icons.Filled.CheckCircle,
                    )
                ),
                "https://assets.materialup.com/uploads/9b34f73f-3e50-40ea-8219-e21d92b63816/preview.png",
                ),
            trackingTypeB = TrackingTypeB(
                timelineList = listOf(
                    TrackingTypeB.Status(
                        id = 1,
                        name = "Your Order has been placed",
                        color = Color.Red
                    ),
                    TrackingTypeB.Status(
                        id = 2,
                        name = "Your Order is on the way",
                        color = Color.Yellow
                    ),
                    TrackingTypeB.Status(
                        name = "Your Order has been delivered",
                        id = 3,
                        color = Color.Green
                    )
                ),
                delivery = TrackingTypeB.Delivery(
                    "Flat no: 35093, A-Wing\n" +
                            "Hiranandani Gardens\n" +
                            "Near I.I.T Powai, Powai Area Mumbai, Maharashtra"
                ),
                "https://assets.materialup.com/uploads/3d12b487-68fc-4d01-80b7-22a45c416bb3/preview.jpg",

                )
        )
    ).also {
        orderDetailsStrategyContext.setContext(it)
    }

    private val _orderDetails = MutableStateFlow(
        orderDetailsStrategyContext.getOrderDetailsDtoStrategy()
    )

    override fun getOrderDetails() = _orderDetails

    override fun updateOrderDetails(updatedOrder: OrderDetailsDto) {
        _orderDetails.update { updatedOrder }
    }


}