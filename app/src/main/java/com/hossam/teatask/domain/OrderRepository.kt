package com.hossam.teatask.domain

import com.hossam.teatask.data.models.OrderDetailsDto
import com.hossam.teatask.data.models.OrderUIStrategy
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun toggleStrategy()
    fun getOrderDetails(): Flow<OrderDetailsDto>
    fun getOrderUI(): Flow<OrderUIStrategy>
}