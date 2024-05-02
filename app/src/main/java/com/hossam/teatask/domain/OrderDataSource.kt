package com.hossam.teatask.domain

import com.hossam.teatask.data.models.OrderDetailsDto
import kotlinx.coroutines.flow.Flow

interface OrderDataSource {
    fun getOrderDetails(): Flow<OrderDetailsDto>
    fun updateOrderDetails(updatedOrder: OrderDetailsDto)
}