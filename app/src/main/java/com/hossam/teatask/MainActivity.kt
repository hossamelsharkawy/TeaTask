package com.hossam.teatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hossam.teatask.ui.order.OrderViewModel
import com.hossam.teatask.ui.order.OrderScreen
import dagger.hilt.android.AndroidEntryPoint

import androidx.lifecycle.viewmodel.compose.viewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: OrderViewModel = viewModel()
            val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

            if ((uiState.orderDetailsDto == null) or (uiState.orderUIStrategy == null)) {
                Text(text = "Loading ...", Modifier.statusBarsPadding())
            } else {
                OrderScreen(
                    uiState.orderDetailsDto!!,
                    uiState.orderUIStrategy!!,
                    viewModel::toggleStrategy
                )
            }
        }
    }
}






