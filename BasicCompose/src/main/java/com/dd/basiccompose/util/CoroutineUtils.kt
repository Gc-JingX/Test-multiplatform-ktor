package com.dd.basiccompose.util

import com.dd.basiccompose.util.log.LogUtils
import kotlinx.coroutines.*

object CoroutineUtils {

    private const val TAG = "CoroutineUtils"

    fun errorHandler(tag: String): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            if (throwable !is CancellationException) {
                var error = ""
                for (e in throwable.stackTrace) {
                    error += e.toString() + "\n"
                }
                LogUtils.e(tag, error)
            }
        }

    /**
     * 返回一个负责IO工作的协程作用域
     * */
    fun getAppCoroutine(tag: String = TAG) = CoroutineScope(
        SupervisorJob() + Dispatchers.IO + errorHandler(tag)
    )


}