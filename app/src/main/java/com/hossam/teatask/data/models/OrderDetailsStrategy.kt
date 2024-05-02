package com.hossam.teatask.data.models

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

interface OrderDetailsStrategy {
    fun createOrderDetailsDto(): OrderDetailsDto
}

class OrderDetailsTypeAStrategy(val data: ConfigurationData) : OrderDetailsStrategy {
    override fun createOrderDetailsDto() = OrderDetailsTypeADto(
        title = data.title,
        imageUrl = data.tracking.trackingTypeA.icon,
        trackingList = data.tracking.trackingTypeA.timelineList
    )
}


class OrderDetailsTypeBStrategy(val data: ConfigurationData) : OrderDetailsStrategy {
    override fun createOrderDetailsDto() = OrderDetailsTypeBDto(
        title = data.title,
        imageUrl = data.tracking.trackingTypeB.icon,
        trackingList = data.tracking.trackingTypeB.timelineList,
        delivery = data.tracking.trackingTypeB.delivery
    )
}

class OrderDetailsStrategyContextImp : OrderDetailsStrategyContext {
    lateinit var data: ConfigurationData
    private val type: String get() = data.tracking.type

    override var state = MutableStateFlow(ConfigurationData.Tracking.Vertical)

    override fun getOrderDetailsDtoStrategy() = when (type) {
        ConfigurationData.Tracking.Vertical -> OrderDetailsTypeAStrategy(data).createOrderDetailsDto()
        else -> OrderDetailsTypeBStrategy(data).createOrderDetailsDto()
    }

    override fun getOrderUIStrategy(): OrderUIStrategy = when (type) {
        ConfigurationData.Tracking.Vertical ->
            OrderUIStrategyTypeA()
        else ->
            OrderUIStrategyTypeB()
    }

    override fun getStatusEmitterStrategy(): StatusEmitterStrategy = when (type) {
        ConfigurationData.Tracking.Vertical -> TrackingTypeAStatusEmitter()
        else -> TrackingTypeBStatusEmitter()
    }

    override fun toggleStrategy() {
        when (type) {
            ConfigurationData.Tracking.Vertical ->
                data.tracking.type = ConfigurationData.Tracking.Horizontal

            else -> data.tracking.type = ConfigurationData.Tracking.Vertical
        }
        state.update { type }
    }

    override fun setContext(data: ConfigurationData) {
        this.data = data
        state.value = data.tracking.type
    }

}

interface OrderDetailsStrategyContext {
    val state: Flow<String>
    fun getStatusEmitterStrategy(): StatusEmitterStrategy
    fun getOrderDetailsDtoStrategy(): OrderDetailsDto
    fun getOrderUIStrategy(): OrderUIStrategy
    fun toggleStrategy()
    fun setContext(data: ConfigurationData)
}