package com.android.systemui.haptics.slider;

import android.view.View;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HapticSliderViewBinder {
    public static final void bind(View view, SeekbarHapticPlugin seekbarHapticPlugin) {
        if (view != null) {
            RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, new HapticSliderViewBinder$bind$1(seekbarHapticPlugin, null));
        }
    }
}
