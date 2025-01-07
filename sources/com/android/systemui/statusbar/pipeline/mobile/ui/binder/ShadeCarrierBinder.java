package com.android.systemui.statusbar.pipeline.mobile.ui.binder;

import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.ShadeCarrierGroupMobileIconViewModel;
import com.android.systemui.util.AutoMarqueeTextView;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeCarrierBinder {
    public static final void bind(AutoMarqueeTextView autoMarqueeTextView, ShadeCarrierGroupMobileIconViewModel shadeCarrierGroupMobileIconViewModel) {
        autoMarqueeTextView.setVisibility(0);
        RepeatWhenAttachedKt.repeatWhenAttached(autoMarqueeTextView, EmptyCoroutineContext.INSTANCE, new ShadeCarrierBinder$bind$1(shadeCarrierGroupMobileIconViewModel, autoMarqueeTextView, null));
    }
}
