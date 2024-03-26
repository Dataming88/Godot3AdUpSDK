package com.adup.godotadplugin.untils

import android.app.Activity
import android.util.Log
import com.bytedance.sdk.openadsdk.TTAdConfig
import com.bytedance.sdk.openadsdk.TTAdSdk

class GodotGroMore(val mediaId:String?,
                   val mediaName:String?) {
    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var mediaId: String? = null
        var mediaName: String? = null

        fun build() = GodotGroMore(mediaId,mediaName)
    }

    /**
     * 初始化 SDK
     */
    fun adnInit(activity: Activity){

        //setp1.1：初始化SDK
        TTAdSdk.init(activity, TTAdConfig.Builder()
            .appId(this.mediaId)
            .appName(this.mediaName)
            .allowShowNotify(true)
            .debug(false)
            /**
             * 使用聚合功能此开关必须设置为true，默认为false，不会初始化聚合模板
             */
            .useMediation(true).build())

        //setp1.2：启动SDK
        TTAdSdk.start(object : TTAdSdk.Callback {
            override fun success() {
                Log.i(
                    "AdUpGotDot",
                    "SDK初始化success: " + TTAdSdk.isSdkReady()
                )
            }
            override fun fail(code: Int, msg: String) {
                Log.i(
                    "AdUpGotDot",
                    "fail:  code = $code msg = $msg"
                )
            }
        })
    }
}