package com.android.systemui.clipboardoverlay;

import android.view.WindowInsets;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ClipboardOverlayController$$ExternalSyntheticLambda2 implements BiConsumer {
    public final /* synthetic */ ClipboardOverlayController f$0;

    @Override // java.util.function.BiConsumer
    public final void accept(Object obj, Object obj2) {
        this.f$0.onInsetsChanged((WindowInsets) obj, ((Integer) obj2).intValue());
    }
}
