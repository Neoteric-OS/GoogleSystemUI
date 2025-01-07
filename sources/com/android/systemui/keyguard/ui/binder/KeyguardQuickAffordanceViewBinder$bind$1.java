package com.android.systemui.keyguard.ui.binder;

import com.android.systemui.animation.view.LaunchableImageView;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardQuickAffordanceViewBinder$bind$1 {
    public final /* synthetic */ RepeatWhenAttachedKt$repeatWhenAttached$1 $disposableHandle;
    public final /* synthetic */ LaunchableImageView $view;

    public KeyguardQuickAffordanceViewBinder$bind$1(LaunchableImageView launchableImageView, RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1) {
        this.$view = launchableImageView;
        this.$disposableHandle = repeatWhenAttachedKt$repeatWhenAttached$1;
    }

    public final void destroy() {
        this.$view.setOnApplyWindowInsetsListener(null);
        this.$disposableHandle.dispose();
    }
}
