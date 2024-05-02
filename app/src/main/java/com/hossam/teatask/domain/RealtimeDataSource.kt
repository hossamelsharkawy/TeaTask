package com.hossam.teatask.domain

import com.hossam.teatask.data.models.TrackingStatus
import kotlinx.coroutines.flow.Flow

interface RealtimeDataSource {
    fun subOrderUpdate(): Flow<TrackingStatus>
    fun emit()
}