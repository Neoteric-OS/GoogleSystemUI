package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import android.graphics.drawable.AnimationDrawable;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.statusbar.StatusBarIconView;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarIconViewBinder$bindColor$$inlined$collectTracingEach$1 implements FlowCollector {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StatusBarIconView $view$inlined;

    public /* synthetic */ StatusBarIconViewBinder$bindColor$$inlined$collectTracingEach$1(StatusBarIconView statusBarIconView, int i) {
        this.$r8$classId = i;
        this.$view$inlined = statusBarIconView;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        boolean isEnabled;
        AnimationDrawable animationDrawable;
        switch (this.$r8$classId) {
            case 0:
                StatusBarIconView statusBarIconView = this.$view$inlined;
                boolean isEnabled2 = Trace.isEnabled();
                if (isEnabled2) {
                    TraceUtilsKt.beginSlice("SBIV#bindColor");
                }
                try {
                    int intValue = ((Number) obj).intValue();
                    statusBarIconView.setStaticDrawableColor(intValue);
                    statusBarIconView.setDecorColor(intValue);
                    if (isEnabled2) {
                        TraceUtilsKt.endSlice();
                    }
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled2) {
                        TraceUtilsKt.endSlice();
                    }
                }
            case 1:
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("SBIV#bindAnimationsEnabled");
                }
                try {
                    boolean booleanValue = ((Boolean) obj).booleanValue();
                    StatusBarIconView statusBarIconView2 = this.$view$inlined;
                    if (statusBarIconView2.mAllowAnimation != booleanValue) {
                        statusBarIconView2.mAllowAnimation = booleanValue;
                        statusBarIconView2.updateAnim();
                        if (!statusBarIconView2.mAllowAnimation && (animationDrawable = statusBarIconView2.mAnim) != null) {
                            animationDrawable.setVisible(statusBarIconView2.getVisibility() == 0, true);
                        }
                    }
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            default:
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("SBIV#bindTintAlpha");
                }
                try {
                    float floatValue = ((Number) obj).floatValue();
                    StatusBarIconView statusBarIconView3 = this.$view$inlined;
                    statusBarIconView3.mDozeAmount = floatValue;
                    statusBarIconView3.updateDecorColor();
                    statusBarIconView3.updateIconColor();
                    return Unit.INSTANCE;
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
        }
    }
}
