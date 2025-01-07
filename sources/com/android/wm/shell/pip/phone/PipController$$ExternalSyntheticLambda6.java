package com.android.wm.shell.pip.phone;

import java.util.function.IntConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipController$$ExternalSyntheticLambda6 implements IntConsumer {
    public final /* synthetic */ PipController f$0;

    public /* synthetic */ PipController$$ExternalSyntheticLambda6(PipController pipController) {
        this.f$0 = pipController;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        PipController pipController = this.f$0;
        pipController.mPipDisplayLayoutState.mDisplayId = i;
        pipController.onDisplayChanged(pipController.mDisplayController.getDisplayLayout(i), false);
    }
}
