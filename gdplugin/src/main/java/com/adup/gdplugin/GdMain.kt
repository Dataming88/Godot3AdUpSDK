package com.adup.gdplugin

import com.adup.godotadplugin.untils.AdUpRewardAd
import com.adup.godotadplugin.untils.GodotGroMore
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.SignalInfo
import org.godotengine.godot.plugin.UsedByGodot

class GdMain(godot: Godot?) : GodotPlugin(godot) {

    private var godotGroMore : GodotGroMore?=null

//    override fun getPluginName() = "Godot3AdUpSDK"

    override fun getPluginName(): String {
        return "Godot3AdUpSDK"
    }

    override fun getPluginMethods(): MutableList<String> {
        return mutableListOf("adnInit","showRewardVideoAd")
    }

    override fun getPluginSignals(): MutableSet<SignalInfo> {
        return mutableSetOf(
            SignalInfo("onRewardVideoAdCallBack",Integer::class.java)
        )
    }

    /**
     * adn初始化
     */
    @UsedByGodot
    private fun adnInit(mediaId:String,mediaName:String){
        godotGroMore = GodotGroMore.build {
            this.mediaId = mediaId
            this.mediaName = mediaName
        }
        activity?.let {
            godotGroMore?.adnInit(it)
        }
    }


    /**
     * 展示激励广告
     */
    @UsedByGodot
    private fun showRewardVideoAd(codeId:String,mediaPlay:Int){
        activity?.let {
            AdUpRewardAd.loadRewardVideoAd(it,codeId,mediaPlay){ code -> emitSignal("onRewardVideoAdCallBack",code) }
        }
    }

}