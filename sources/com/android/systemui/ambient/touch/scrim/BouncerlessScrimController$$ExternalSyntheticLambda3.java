package com.android.systemui.ambient.touch.scrim;

import com.android.systemui.dreams.DreamOverlayContainerViewController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class BouncerlessScrimController$$ExternalSyntheticLambda3 implements Consumer {
    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        DreamOverlayContainerViewController.this.mWakingUpFromSwipe = true;
    }
}
