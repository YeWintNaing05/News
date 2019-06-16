package com.ywn.data.executor

import com.ywn.domain.executor.ThreadExecutor
import io.reactivex.annotations.NonNull
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * created by Ye Witn Naing 5/26/2019
 */

@Singleton
class JobExecutor @Inject
internal constructor() : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        this.threadPoolExecutor = ThreadPoolExecutor(
            3, 5, 10L, TimeUnit.SECONDS, LinkedBlockingQueue(), JobThreadFactory()
        )
    }


    override fun execute(@NonNull runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(@NonNull runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}