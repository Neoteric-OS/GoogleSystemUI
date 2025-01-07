package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.shade.NotificationPanelViewController;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToAodFoldTransitionInteractor {
    public final ToAodFoldTransitionInteractor$foldAnimator$1 foldAnimator = new ToAodFoldTransitionInteractor$foldAnimator$1(this);
    public final KeyguardClockInteractor keyguardClockInteractor;
    public NotificationPanelViewController.ShadeFoldAnimatorImpl parentAnimator;

    static {
        Intrinsics.checkNotNull(Reflection.getOrCreateKotlinClass(ToAodFoldTransitionInteractor.class).getSimpleName());
    }

    public ToAodFoldTransitionInteractor(KeyguardClockInteractor keyguardClockInteractor) {
        this.keyguardClockInteractor = keyguardClockInteractor;
    }
}
