package com.android.systemui.temporarydisplay;

import android.view.ViewGroup;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController;
import com.android.systemui.util.wakelock.WakeLock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TemporaryViewDisplayController$removeViewFromWindow$1 implements Runnable {
    public final /* synthetic */ TemporaryViewDisplayController.DisplayInfo $displayInfo;
    public final /* synthetic */ ViewGroup $view;
    public final /* synthetic */ TemporaryViewDisplayController this$0;

    public TemporaryViewDisplayController$removeViewFromWindow$1(TemporaryViewDisplayController temporaryViewDisplayController, TemporaryViewDisplayController.DisplayInfo displayInfo, ViewGroup viewGroup) {
        this.this$0 = temporaryViewDisplayController;
        this.$displayInfo = displayInfo;
        this.$view = viewGroup;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.this$0.logger.logViewRemovedFromWindowManager(this.$displayInfo.info, this.$view, false);
        this.this$0.windowManager.removeView(this.$view);
        TemporaryViewDisplayController.DisplayInfo displayInfo = this.$displayInfo;
        WakeLock wakeLock = displayInfo.wakeLock;
        if (wakeLock != null) {
            wakeLock.release(displayInfo.info.getWakeReason());
        }
    }
}
