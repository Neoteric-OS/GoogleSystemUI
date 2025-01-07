package com.android.systemui.dreams;

import android.util.MathUtils;
import com.android.systemui.doze.util.BurnInHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class DreamOverlayContainerViewController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DreamOverlayContainerViewController f$0;

    public /* synthetic */ DreamOverlayContainerViewController$$ExternalSyntheticLambda0(DreamOverlayContainerViewController dreamOverlayContainerViewController) {
        this.f$0 = dreamOverlayContainerViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DreamOverlayContainerViewController dreamOverlayContainerViewController = this.f$0;
        dreamOverlayContainerViewController.getClass();
        long currentTimeMillis = System.currentTimeMillis() - dreamOverlayContainerViewController.mJitterStartTimeMillis;
        long j = dreamOverlayContainerViewController.mMillisUntilFullJitter;
        int i = dreamOverlayContainerViewController.mMaxBurnInOffset;
        if (currentTimeMillis < j) {
            i = Math.round(MathUtils.lerp(0.0f, i, currentTimeMillis / j));
        }
        int i2 = i / 2;
        int burnInOffset = BurnInHelperKt.getBurnInOffset(i, true) - i2;
        int burnInOffset2 = BurnInHelperKt.getBurnInOffset(i, false) - i2;
        ((DreamOverlayContainerView) dreamOverlayContainerViewController.mView).setTranslationX(burnInOffset);
        ((DreamOverlayContainerView) dreamOverlayContainerViewController.mView).setTranslationY(burnInOffset2);
        dreamOverlayContainerViewController.mHandler.postDelayed(new DreamOverlayContainerViewController$$ExternalSyntheticLambda0(dreamOverlayContainerViewController), dreamOverlayContainerViewController.mBurnInProtectionUpdateInterval);
    }
}
