package com.android.systemui.statusbar.chips.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.animation.view.LaunchableLinearLayout;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipBackgroundContainer extends LaunchableLinearLayout {
    public Function0 maxHeightFetcher;

    public ChipBackgroundContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int measuredHeight;
        super.onMeasure(i, i2);
        Function0 function0 = this.maxHeightFetcher;
        Integer num = function0 != null ? (Integer) function0.invoke() : null;
        if (num != null) {
            measuredHeight = getMeasuredHeight();
            int intValue = num.intValue() - 1;
            if (measuredHeight > intValue) {
                measuredHeight = intValue;
            }
        } else {
            measuredHeight = getMeasuredHeight();
        }
        setMeasuredDimension(getMeasuredWidth(), measuredHeight);
    }
}
