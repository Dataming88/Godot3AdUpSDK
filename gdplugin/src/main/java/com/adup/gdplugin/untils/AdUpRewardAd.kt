package com.adup.godotadplugin.untils

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.bytedance.sdk.openadsdk.AdSlot
import com.bytedance.sdk.openadsdk.TTAdNative.RewardVideoAdListener
import com.bytedance.sdk.openadsdk.TTAdSdk
import com.bytedance.sdk.openadsdk.TTRewardVideoAd
import com.bytedance.sdk.openadsdk.TTRewardVideoAd.RewardAdInteractionListener
import com.bytedance.sdk.openadsdk.mediation.ad.MediationAdSlot

object AdUpRewardAd {
    private var mTTRewardVideoAd: TTRewardVideoAd? = null // 激励广告对象

    private var mRewardVideoListener: RewardVideoAdListener? = null // 广告加载监听器

    private var mRewardVideoAdInteractionListener: RewardAdInteractionListener? = null // 广告展示监听器


    fun loadRewardVideoAd(activity: Activity, codeId:String,mediaPlay:Int,block:(code:Int)-> Unit) {
        val adslot = AdSlot.Builder()
            .setCodeId(codeId)
            .setOrientation(mediaPlay)
            .setMediationAdSlot(
                MediationAdSlot.Builder().build()
            )
            .build()

        /** 2、创建TTAdNative对象  */
        val adNativeLoader = TTAdSdk.getAdManager().createAdNative(activity)
        /** 1、创建AdSlot对象  */
        /** 3、创建加载、展示监听器  */
        initListeners(activity, block)
        /** 4、加载广告  */
        adNativeLoader.loadRewardVideoAd(adslot, mRewardVideoListener)
    }

    // 广告加载成功后，开始展示广告
    private fun showRewardVideoAds(activity: Activity) {
        if (mTTRewardVideoAd == null) {
            Log.i("UMAdDemo", "请先加载广告或等待广告加载完毕后再调用show方法")
            return
        }
        /** 5、设置展示监听器，展示广告  */
        mTTRewardVideoAd!!.setRewardAdInteractionListener(mRewardVideoAdInteractionListener)
        mTTRewardVideoAd!!.showRewardVideoAd(activity)
    }

    private fun initListeners(activity: Activity, block:(code:Int)-> Unit) {
        mRewardVideoListener = object : RewardVideoAdListener {
            override fun onError(i: Int, s: String) {
                Log.i("UMAdDemo", "reward load fail: errCode: $i, errMsg: $s")
                // 获取失败
                block(500)
            }

            override fun onRewardVideoAdLoad(ttRewardVideoAd: TTRewardVideoAd) {
                mTTRewardVideoAd = ttRewardVideoAd
                Log.i("UMAdDemo", "reward  load success")
                block(200)
            }

            override fun onRewardVideoCached() {
                Log.i("UMAdDemo", "reward cached success")
            }

            override fun onRewardVideoCached(ttRewardVideoAd: TTRewardVideoAd) {
                mTTRewardVideoAd = ttRewardVideoAd
                showRewardVideoAds(activity)
            }
        }
        mRewardVideoAdInteractionListener = object : RewardAdInteractionListener {
            override fun onAdShow() {
                Log.i("UMAdDemo", "reward show")
                block(201)
            }

            override fun onAdVideoBarClick() {
                Log.i("UMAdDemo", "reward click")
            }

            override fun onAdClose() {
                Log.i("UMAdDemo", "reward close")
                block(202)
            }

            override fun onVideoComplete() {
                Log.i("UMAdDemo", "reward onVideoComplete")
                block(203)
            }

            override fun onVideoError() {
                Log.i("UMAdDemo", "reward onVideoError")
                block(204)
            }

            override fun onRewardVerify(b: Boolean, i: Int, s: String, i1: Int, s1: String) {
                Log.i("UMAdDemo", "reward onRewardVerify")
            }

            override fun onRewardArrived(b: Boolean, i: Int, bundle: Bundle) {
                Log.i("UMAdDemo", "reward onRewardArrived")
            }

            override fun onSkippedVideo() {
                Log.i("UMAdDemo", "reward onSkippedVideo")
            }
        }
    }

}