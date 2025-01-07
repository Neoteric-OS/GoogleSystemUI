package com.android.systemui.shade;

import android.view.DisplayCutout;
import android.view.WindowInsets;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsQSContainerController$delayedInsetSetter$1 implements Runnable, Consumer {
    public ExecutorImpl.ExecutionToken canceller;
    public int cutoutInsets;
    public int stableInsets;
    public final /* synthetic */ NotificationsQSContainerController this$0;

    public NotificationsQSContainerController$delayedInsetSetter$1(NotificationsQSContainerController notificationsQSContainerController) {
        this.this$0 = notificationsQSContainerController;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        WindowInsets windowInsets = (WindowInsets) obj;
        this.stableInsets = windowInsets.getStableInsetBottom();
        DisplayCutout displayCutout = windowInsets.getDisplayCutout();
        this.cutoutInsets = displayCutout != null ? displayCutout.getSafeInsetBottom() : 0;
        ExecutorImpl.ExecutionToken executionToken = this.canceller;
        if (executionToken != null) {
            executionToken.run();
        }
        this.canceller = this.this$0.delayableExecutor.executeDelayed(this, 500L);
    }

    @Override // java.lang.Runnable
    public final void run() {
        NotificationsQSContainerController notificationsQSContainerController = this.this$0;
        notificationsQSContainerController.bottomStableInsets = this.stableInsets;
        notificationsQSContainerController.bottomCutoutInsets = this.cutoutInsets;
        notificationsQSContainerController.updateBottomSpacing();
    }
}
