package com.ywn.common

import android.os.Looper
import com.ywn.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a {@link Scheduler}
 * which will execute actions on the Android UI thread
 */
@Singleton
class UIThread @Inject constructor() : PostExecutionThread {

  override val scheduler: Scheduler
    get() = {
      val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
      RxAndroidPlugins.setInitMainThreadSchedulerHandler {
        asyncMainThreadScheduler
      }
      AndroidSchedulers.mainThread()
    }.invoke()

}