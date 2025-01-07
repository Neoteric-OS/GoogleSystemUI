package com.android.systemui.util;

import android.content.Context;
import android.os.Trace;
import android.util.AttributeSet;
import android.view.Choreographer;
import androidx.constraintlayout.motion.widget.MotionLayout;
import com.android.app.tracing.TraceUtilsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NoRemeasureMotionLayout extends MotionLayout {
    public Long lastFrame;
    public Integer lastHeightSpec;
    public Integer lastWidthSpec;

    public NoRemeasureMotionLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    @Override // androidx.constraintlayout.motion.widget.MotionLayout, androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        Integer num;
        Integer num2 = this.lastWidthSpec;
        if (num2 != null && num2.intValue() == i && (num = this.lastHeightSpec) != null && num.intValue() == i2) {
            Choreographer mainThreadInstance = Choreographer.getMainThreadInstance();
            if (Intrinsics.areEqual(mainThreadInstance != null ? Long.valueOf(mainThreadInstance.getFrameTime()) : null, this.lastFrame)) {
                setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
                return;
            }
        }
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("NoRemeasureMotionLayout - measure");
        }
        try {
            super.onMeasure(i, i2);
            this.lastWidthSpec = Integer.valueOf(i);
            this.lastHeightSpec = Integer.valueOf(i2);
            Choreographer mainThreadInstance2 = Choreographer.getMainThreadInstance();
            this.lastFrame = mainThreadInstance2 != null ? Long.valueOf(mainThreadInstance2.getFrameTime()) : null;
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        } catch (Throwable th) {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
            throw th;
        }
    }

    public /* synthetic */ NoRemeasureMotionLayout(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public NoRemeasureMotionLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
