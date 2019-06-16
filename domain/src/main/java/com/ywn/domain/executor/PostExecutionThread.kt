package com.ywn.domain.executor

import io.reactivex.Scheduler

/**
 * created by Ye Witn Naing 5/26/2019
 */

interface PostExecutionThread {
    val scheduler: Scheduler
}