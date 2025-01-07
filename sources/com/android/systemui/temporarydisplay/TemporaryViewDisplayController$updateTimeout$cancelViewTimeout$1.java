package com.android.systemui.temporarydisplay;

import com.android.systemui.temporarydisplay.TemporaryViewDisplayController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TemporaryViewDisplayController$updateTimeout$cancelViewTimeout$1 implements Runnable {
    public final /* synthetic */ TemporaryViewDisplayController.DisplayInfo $displayInfo;
    public final /* synthetic */ TemporaryViewDisplayController this$0;

    public TemporaryViewDisplayController$updateTimeout$cancelViewTimeout$1(TemporaryViewDisplayController temporaryViewDisplayController, TemporaryViewDisplayController.DisplayInfo displayInfo) {
        this.this$0 = temporaryViewDisplayController;
        this.$displayInfo = displayInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.removeView(this.$displayInfo.info.getId(), "TIMEOUT");
    }
}
