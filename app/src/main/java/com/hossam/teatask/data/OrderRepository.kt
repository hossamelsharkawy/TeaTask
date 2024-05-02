package com.hossam.teatask.data

import com.hossam.teatask.data.models.OrderDetailsDto
import com.hossam.teatask.data.models.OrderDetailsStrategyContext
import com.hossam.teatask.data.models.OrderUIStrategy
import com.hossam.teatask.domain.OrderDataSource
import com.hossam.teatask.domain.OrderRepository
import com.hossam.teatask.domain.RealtimeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import javax.inject.Inject


class OrderRepositoryImp @Inject constructor(
    private val orderDataSource: OrderDataSource,
    private var orderDetailsStrategyContext: OrderDetailsStrategyContext,
    private val realTimeDataSource: RealtimeDataSource,
) : OrderRepository {

    init {
        realTimeDataSource.emit()

    }
      override fun getOrderDetails(): Flow<OrderDetailsDto> =
          merge(
              orderDataSource.getOrderDetails(),
              realTimeDataSource.subOrderUpdate()
                  .map {
                      orderDataSource.getOrderDetails()
                          .first()
                          .updateStatus(it)
                          .also {
                              orderDataSource.updateOrderDetails(it)
                          }
                  }
          )

    override fun getOrderUI(): Flow<OrderUIStrategy> = flow {
        emit(orderDetailsStrategyContext.getOrderUIStrategy())
    }

    override fun toggleStrategy() {
        orderDetailsStrategyContext.toggleStrategy()
        orderDataSource.updateOrderDetails(orderDetailsStrategyContext.getOrderDetailsDtoStrategy())
        realTimeDataSource.emit()
    }


}







