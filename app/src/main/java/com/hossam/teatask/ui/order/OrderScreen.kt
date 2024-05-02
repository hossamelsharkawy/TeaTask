package com.hossam.teatask.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hossam.teatask.data.models.OrderDetailsDto
import com.hossam.teatask.data.models.OrderUIStrategy
import com.hossam.teatask.ui.theme.TeaTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    order: OrderDetailsDto,
    orderUIStrategy: OrderUIStrategy,
    function: () -> Unit,
) = with(order) {
    TeaTaskTheme {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = title) },
                    actions = {
                        IconButton(onClick = function::invoke) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription =
                                ""
                            )
                        }
                    }

                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = order.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                orderUIStrategy.DrawUI(order)

            }
        }
    }

}