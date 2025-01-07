package com.android.systemui.statusbar.phone;

import android.os.TraceNameSupplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnlockedScreenOffAnimationController$special$$inlined$namedRunnable$1 implements Runnable, TraceNameSupplier {
    public final /* synthetic */ UnlockedScreenOffAnimationController this$0;

    public UnlockedScreenOffAnimationController$special$$inlined$namedRunnable$1(UnlockedScreenOffAnimationController unlockedScreenOffAnimationController) {
        this.this$0 = unlockedScreenOffAnimationController;
    }

    public final String getTraceName() {
        return "startLightReveal";
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = this.this$0;
        unlockedScreenOffAnimationController.lightRevealAnimationPlaying = true;
        unlockedScreenOffAnimationController.lightRevealAnimator.start();
    }
}
