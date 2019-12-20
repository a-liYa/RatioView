package com.aliya.view.ratio;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 宽高比 - 助手
 * 规则：
 * 1. 根据精确的宽/高 结合比例计算 高/宽；
 * 2. 如果宽和高均为精确值，以最大不超出原则重新计算宽或高。
 *
 * @author a_liYa
 * @date 2017/7/18 23:00.
 */
public class RatioHelper {

    private float ratio_w2h = NO_VALUE;

    private static final String RATIO_SYMBOL = ":";
    static final int NO_VALUE = -1;

    public RatioHelper(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray ta = context.obtainStyledAttributes(attrs,
                com.aliya.view.ratio.R.styleable.RatioLayout);
        String w_h = ta.getString(com.aliya.view.ratio.R.styleable.RatioLayout_ratio_w2h);
        if (!TextUtils.isEmpty(w_h) && w_h.contains(RATIO_SYMBOL)) {
            String[] split = w_h.trim().split(RATIO_SYMBOL);
            if (split != null && split.length == 2) {
                try {
                    ratio_w2h = Float.parseFloat(split[0].trim())
                            / Float.parseFloat(split[1].trim());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        ta.recycle();
    }

    public float getRatio() {
        return ratio_w2h;
    }

    public RatioHelper setRatio(float w2h) {
        this.ratio_w2h = w2h;
        return this;
    }

    public int[] measureSpec(int[] measureSpecs, LayoutParams params) {
        if (ratio_w2h > 0) {
            int wMode = MeasureSpec.getMode(measureSpecs[0]);
            int hMode = MeasureSpec.getMode(measureSpecs[1]);

            int wSize = MeasureSpec.getSize(measureSpecs[0]);
            int hSize = MeasureSpec.getSize(measureSpecs[1]);

            if ((wMode != MeasureSpec.EXACTLY && hMode == MeasureSpec.EXACTLY) ||
                    (params.width == WRAP_CONTENT && params.height != WRAP_CONTENT)) {
                measureSpecs[0] = MeasureSpec
                        .makeMeasureSpec(Math.round(hSize * ratio_w2h), MeasureSpec.EXACTLY);
            } else if ((wMode == MeasureSpec.EXACTLY && hMode != MeasureSpec.EXACTLY) ||
                    (params.width != WRAP_CONTENT && params.height == WRAP_CONTENT)) {
                measureSpecs[1] = MeasureSpec
                        .makeMeasureSpec(Math.round(wSize / ratio_w2h), MeasureSpec.EXACTLY);
            } else if ((wMode == MeasureSpec.EXACTLY && hMode == MeasureSpec.EXACTLY) ||
                    (params.width != WRAP_CONTENT && params.height != WRAP_CONTENT)) {
                int _wSize = Math.round(hSize * ratio_w2h);
                if (_wSize <= wSize) {
                    measureSpecs[0] = MeasureSpec
                            .makeMeasureSpec(_wSize, MeasureSpec.EXACTLY);
                } else {
                    measureSpecs[1] = MeasureSpec
                            .makeMeasureSpec(Math.round(wSize / ratio_w2h), MeasureSpec.EXACTLY);
                }
            }
        }
        return measureSpecs;
    }
}
