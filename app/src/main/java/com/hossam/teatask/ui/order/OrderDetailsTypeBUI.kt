package com.hossam.teatask.ui.order

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.hossam.teatask.data.models.OrderDetailsTypeBDto
import com.hossam.teatask.ui.theme.Title

@Composable
fun OrderDetailsTypeBUI(order: OrderDetailsTypeBDto) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Title(
                order.delivery.address,
                modifier = Modifier.fillMaxWidth(fraction = 0.8f),
            )

            Text(
                order.getDateReadable(),
                modifier = Modifier.size(100.dp),
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            order.getTrackingStatusList().fastForEach {
                Icon(
                    imageVector = it.getCurrentIcon(),
                    contentDescription = "",
                    tint = it.getCurrentColor()
                )
            }
        }

        AnimatedContent(targetState = order.getLastStatus(), label = "") { text ->
            Title(text = text)
        }

    }


}

