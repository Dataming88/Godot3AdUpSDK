
# Godot3AdUpSDK 使用说明

此源码包括sdk与godot两部分的完整项目，下载后导入到3.5.x版本的引擎中便可使用，3.5以下的版本没有测试过，应该都是通用的。
* 支持引擎版本：Godot 3.5.x

### 包含以下功能：
* 激励广告
_________________
## 安装方法
1. 复制项目本目录下android/plugins文件内全部内容到你的项目中或者自行编译android/build/gdplugin模块，将编译好的aar复制到你的项目中。
3. 复制GodotTapTap.gd文件到你的项目中，并添加到自动加载中作为单例存在。
4. 项目导出时在**自定义构建** 中勾选启动，以及插件中勾选Godot 3 Ad Up SDK，**将最小SDK设置为21（3.5默认为19）**。
_________________
## 使用方法
```
来自激励广告的信号
# 500 == 广告加载失败
# 200 == 广告加载成功 可以播放
# 201 == 激励广告已显示
# 202 == 激励广告已经关闭
# 203 == 视频播放结束
# 204 == 视频出错
signal onRewardVideoAdCallBack(code)
```
## 函数说明
### 广告模块
```
func initAd(mediaId,mediaName)
```
> #### 初始化广告sdk
> - mediaId,mediaName 应用位信息

```
func showRewardVideoAd(codeId,mediaPlay)
```
> #### 播放激励广告
> - codeId,mediaPlay codeId: 广告位id  mediaPlay: 1=> 竖屏 2=>横屏
