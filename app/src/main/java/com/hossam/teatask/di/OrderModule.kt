package com.hossam.teatask.di

import com.hossam.teatask.data.OrderDataSourceImp

import com.hossam.teatask.data.OrderRepositoryImp
import com.hossam.teatask.data.RealtimeDataSourceImp
import com.hossam.teatask.data.models.OrderDetailsStrategyContext
import com.hossam.teatask.data.models.OrderDetailsStrategyContextImp
import com.hossam.teatask.domain.OrderDataSource
import com.hossam.teatask.domain.OrderRepository
import com.hossam.teatask.domain.RealtimeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
object OrderModule {

    @Provides
    fun provideOrderRepository(
        orderDataSource: OrderDataSource,
        orderDetailsStrategyContext: OrderDetailsStrategyContext,
        realtimeDataSource: RealtimeDataSource,
    ): OrderRepository =
        OrderRepositoryImp(orderDataSource, orderDetailsStrategyContext, realtimeDataSource)

    @Provides
    fun provideOrderDataSource(
        orderDetailsStrategyContext: OrderDetailsStrategyContext,
    ): OrderDataSource = OrderDataSourceImp(orderDetailsStrategyContext)

    @Provides
    fun provideRealtimeDataSource(
        orderDetailsStrategyContext: OrderDetailsStrategyContext,
    ): RealtimeDataSource =
        RealtimeDataSourceImp(orderDetailsStrategyContext)


}


@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides

    fun provideOrderDetailsStrategyContext(): OrderDetailsStrategyContext =
        OrderDetailsStrategyContextImp()

}