package com.hossam.teatask.ui.order

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.hossam.teatask.data.models.OrderDetailsTypeADto
import com.hossam.teatask.ui.theme.SubTitle
import com.hossam.teatask.ui.theme.Title


@Composable
fun OrderDetailsTypeAUI(order: OrderDetailsTypeADto) {
    LazyColumn(Modifier.fillMaxWidth()) {
        items(order.getTrackingStatusList()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(if (it.isCompleted) 1f else 0.5f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = it.icon,
                    "",
                )

                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .height(100.dp)
                ) {
                    Title(text = it.name)
                    val time = it.getDateReadable()
                    AnimatedVisibility(visible = time != "") {
                        SubTitle(text = time)
                    }
                }
            }

        }
    }

}