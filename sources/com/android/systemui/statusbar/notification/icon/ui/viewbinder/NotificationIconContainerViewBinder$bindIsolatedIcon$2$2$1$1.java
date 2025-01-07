package com.android.systemui.statusbar.notification.icon.ui.viewbinder;

import com.android.systemui.util.ui.AnimatedValue;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1 implements Runnable {
    public final /* synthetic */ AnimatedValue $tmp0;

    public NotificationIconContainerViewBinder$bindIsolatedIcon$2$2$1$1(AnimatedValue animatedValue) {
        this.$tmp0 = animatedValue;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // java.lang.Runnable
    public final void run() {
        AnimatedValue animatedValue = this.$tmp0;
        if (animatedValue instanceof AnimatedValue.Animating) {
            ((AnimatedValue.Animating) animatedValue).onStopAnimating.invoke();
        }
    }
}
