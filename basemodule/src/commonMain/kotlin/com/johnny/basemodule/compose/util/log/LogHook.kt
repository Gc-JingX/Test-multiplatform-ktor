package com.dd.basiccompose.util.log

import com.dd.utils.log.LogInfo

/** 拦截日志 */
interface LogHook {

    fun hook(info: LogInfo)

}