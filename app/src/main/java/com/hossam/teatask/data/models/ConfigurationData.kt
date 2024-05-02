package com.hossam.teatask.data.models

class ConfigurationData(
    val title: String,
    val tracking: Tracking,
) {

    class Tracking(
        var type: String ,
        val trackingTypeA: TrackingTypeA,
        val trackingTypeB: TrackingTypeB,
    ) {
        companion object {
            const val Vertical = "Vertical"
            const val Horizontal = "Horizontal"
        }
    }
}