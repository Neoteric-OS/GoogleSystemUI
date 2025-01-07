package com.android.systemui.biometrics;

import android.os.Trace;
import android.view.WindowManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.systemui.biometrics.ui.view.UdfpsTouchOverlay;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1 implements Runnable {
    public final /* synthetic */ UdfpsTouchOverlay $view$inlined;
    public final /* synthetic */ UdfpsControllerOverlay this$0;

    public UdfpsControllerOverlay$addViewNowOrLater$$inlined$Runnable$1(UdfpsControllerOverlay udfpsControllerOverlay, UdfpsTouchOverlay udfpsTouchOverlay) {
        this.this$0 = udfpsControllerOverlay;
        this.$view$inlined = udfpsTouchOverlay;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Trace.setCounter("UdfpsAddView", 1L);
        UdfpsControllerOverlay udfpsControllerOverlay = this.this$0;
        ViewCaptureAwareWindowManager viewCaptureAwareWindowManager = udfpsControllerOverlay.windowManager;
        UdfpsTouchOverlay udfpsTouchOverlay = this.$view$inlined;
        WindowManager.LayoutParams layoutParams = udfpsControllerOverlay.coreLayoutParams;
        udfpsControllerOverlay.updateDimensions(layoutParams);
        viewCaptureAwareWindowManager.addView(udfpsTouchOverlay, layoutParams);
    }
}
