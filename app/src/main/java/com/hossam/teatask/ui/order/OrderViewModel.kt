package com.hossam.teatask.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossam.teatask.data.models.OrderDetailsDto
import com.hossam.teatask.data.models.OrderDetailsStrategyContext
import com.hossam.teatask.data.models.OrderUIStrategy
import com.hossam.teatask.data.models.OrderUIStrategyTypeA
import com.hossam.teatask.domain.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
) : ViewModel() {


    val uiStateFlow = MutableStateFlow(UIState())

    data class UIState(
        var orderUIStrategy: OrderUIStrategy? = null,
        var orderDetailsDto: OrderDetailsDto? = null,
    )

    fun toggleStrategy() {
        orderRepository.toggleStrategy()
        getData()
    }

    private fun update(function: (UIState) -> UIState) = uiStateFlow.update(function)

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            combine(
                orderRepository.getOrderDetails(),
                orderRepository.getOrderUI(),
            ) { order, orderUI ->
                update {
                    it.copy(
                        orderDetailsDto = order,
                        orderUIStrategy = orderUI,
                    )
                }
            }.collect()
        }
    }


}