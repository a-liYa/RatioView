# RatioView
实现固定宽高比的控件

#### 依赖

```
compile 'com.aliya:ratioview:2.0.0'
```

#### 实现三大布局

* 1. RatioFrameLayout
* 2. RatioLinearLayout
* 3. RatioRelativeLayout

#### 用法

```
<com.aliya.view.ratio.RatioFrameLayout
    android:layout_width="wrap_content"
    android:layout_height="100dp"
    app:ratio_w2h="1:1">
```

`注意：宽高属性，必须有且仅有一个MeasureSpec.EXACTLY（精确） `