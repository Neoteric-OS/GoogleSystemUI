package com.android.systemui.keyguard.ui.binder;

import android.content.Context;
import com.android.systemui.keyguard.shared.model.ClockSizeSetting;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardPreviewClockViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.shared.clocks.ClockRegistry;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.reflect.KFunction;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardPreviewClockViewBinder {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClockSizeSetting.values().length];
            try {
                ClockSizeSetting.Companion companion = ClockSizeSetting.Companion;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                ClockSizeSetting.Companion companion2 = ClockSizeSetting.Companion;
                iArr[1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void bind(Context context, KeyguardRootView keyguardRootView, KeyguardPreviewClockViewModel keyguardPreviewClockViewModel, ClockRegistry clockRegistry, KFunction kFunction) {
        RepeatWhenAttachedKt.repeatWhenAttached(keyguardRootView, EmptyCoroutineContext.INSTANCE, new KeyguardPreviewClockViewBinder$bind$3(keyguardPreviewClockViewModel, kFunction, context, keyguardRootView, clockRegistry, null));
    }
}
