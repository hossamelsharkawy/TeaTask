package com.hossam.teatask.data.models


import androidx.compose.ui.util.fastLastOrNull
import androidx.compose.ui.util.fastMap
import com.hossam.teatask.utlis.timestampToTime


data class OrderDetailsTypeADto(
    override var title: String,
    override var imageUrl: String,
    override var trackingList: List<TrackingStatus>,
) : OrderDetailsDto {

    override fun updateStatus(updatedStatus: TrackingStatus) =
        this.copy(trackingList = trackingList.fastMap { if (it.id == updatedStatus.id) it.update() else it })

    fun getTrackingStatusList() = trackingList as List<TrackingTypeA.Status>

}

data class OrderDetailsTypeBDto(
    override var title: String,
    override var imageUrl: String,
    override var trackingList: List<TrackingStatus>,
    var delivery: TrackingTypeB.Delivery,
) : OrderDetailsDto {

    fun getTrackingStatusList() = trackingList as List<TrackingTypeB.Status>

    override fun updateStatus(updatedStatus: TrackingStatus) =
        this.copy(trackingList = trackingList.fastMap { if (it.id == updatedStatus.id) it.update() else it })

    fun getDateReadable() =
        trackingList.fastLastOrNull { it.isCompleted }?.time?.timestampToTime() ?: ""

    fun getLastStatus() = trackingList.fastLastOrNull { it.isCompleted }?.name ?: ""

}


interface OrderDetailsDto {
    var title: String
    var imageUrl: String
    var trackingList: List<TrackingStatus>
    fun updateStatus(updatedStatus: TrackingStatus): OrderDetailsDto
}




