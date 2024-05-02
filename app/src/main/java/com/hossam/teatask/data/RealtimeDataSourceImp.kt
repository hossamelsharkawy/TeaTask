package com.hossam.teatask.data

import androidx.compose.ui.util.fastForEach
import com.hossam.teatask.data.models.OrderDetailsStrategyContext
import com.hossam.teatask.data.models.TrackingStatus
import com.hossam.teatask.domain.RealtimeDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class RealtimeDataSourceImp @Inject constructor(
    private var orderDetailsStrategyContext: OrderDetailsStrategyContext,
) : RealtimeDataSource {

    private val backgroundScope = CoroutineScope(SupervisorJob())

    private var mutableStateFlow = MutableSharedFlow<TrackingStatus>()

    override fun subOrderUpdate(): Flow<TrackingStatus> = mutableStateFlow

    override fun emit() {
        backgroundScope.launch {
            orderDetailsStrategyContext.getStatusEmitterStrategy().getStatusList().fastForEach {
                delay(3000)
                mutableStateFlow.emit(it)
            }
        }
    }

}

