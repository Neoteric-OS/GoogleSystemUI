package com.android.systemui.clipboardoverlay;

import com.android.systemui.clipboardoverlay.ClipboardOverlayController;
import com.android.systemui.screenshot.TimeoutHandler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ClipboardOverlayController$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ClipboardOverlayController$$ExternalSyntheticLambda9(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((TimeoutHandler) obj).resetTimeout();
                break;
            default:
                ((ClipboardOverlayController.AnonymousClass4) obj).this$0.animateIn();
                break;
        }
    }
}
