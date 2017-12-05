# FindZhihu

具体分析了知乎如何使用一个 CoordinatorLayout 来实现 MainActivity + 首页 ListFragment +  详情 DetailFragment 的三个页面的view联动效果.

![FindZhihu.gif](http://upload-images.jianshu.io/upload_images/2470338-40a68daa8057e683.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 描述
**MainActivity** 包含底部的 TabLayout, ViewPager. 联动隐藏 TabLayout 是依靠 *Behavior* 实现
ViewPager 里的 Fragment为
  1. **ListFragment** 包含顶部的Toolbar, 联动隐藏 ToolBar 是通过 *FrameInterceptLayout* 实现
  2. **DetailFragment** 点击时替换了 ListFragment, 自己有独立的 TabLayout 和 Toolbar. 联动隐藏 ToolBar 和 TabLayout 是通过 *ObservableScrollView* 实现
![](https://raw.githubusercontent.com/sunxlfred/RES/master/showRelationship.jpg)
## 使用的三方
* [ObservableScrollView](https://github.com/ksoichiro/Android-ObservableScrollView)
* [知乎4.1.8包](https://github.com/sunxlfred/RES/raw/master/zhihu4_1_8_477.apk
) 查找历史包我是通过酷安app端的历史版本下载功能, 不过能看到的版本有限, 目前已经找不到了
