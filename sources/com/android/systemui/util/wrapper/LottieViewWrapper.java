package com.android.systemui.util.wrapper;

import android.os.Trace;
import com.airbnb.lottie.LottieAnimationView;
import com.android.app.tracing.TraceUtilsKt;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LottieViewWrapper extends LottieAnimationView {
    @Override // com.airbnb.lottie.LottieAnimationView, android.view.View
    public final void invalidate() {
        String str = Reflection.getOrCreateKotlinClass(getClass()) + " invalidate";
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice(str);
        }
        try {
            super.invalidate();
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
