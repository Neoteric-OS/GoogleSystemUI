package com.android.systemui.controls.controller;

import android.util.Log;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsProviderLifecycleManager$maybeBindAndLoad$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ControlsBindingControllerImpl.LoadSubscriber $subscriber;
    public final /* synthetic */ ControlsProviderLifecycleManager this$0;

    public /* synthetic */ ControlsProviderLifecycleManager$maybeBindAndLoad$1(ControlsProviderLifecycleManager controlsProviderLifecycleManager, ControlsBindingControllerImpl.LoadSubscriber loadSubscriber, int i) {
        this.$r8$classId = i;
        this.this$0 = controlsProviderLifecycleManager;
        this.$subscriber = loadSubscriber;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ControlsProviderLifecycleManager controlsProviderLifecycleManager = this.this$0;
                Log.d(controlsProviderLifecycleManager.TAG, "Timeout waiting onLoad for " + controlsProviderLifecycleManager.componentName);
                this.$subscriber.onError(this.this$0.token, "Timeout waiting onLoad");
                ControlsProviderLifecycleManager controlsProviderLifecycleManager2 = this.this$0;
                ExecutorImpl.ExecutionToken executionToken = controlsProviderLifecycleManager2.onLoadCanceller;
                if (executionToken != null) {
                    executionToken.run();
                }
                controlsProviderLifecycleManager2.onLoadCanceller = null;
                ((ExecutorImpl) controlsProviderLifecycleManager2.executor).execute(new ControlsProviderLifecycleManager$bindService$1(controlsProviderLifecycleManager2, false, false));
                break;
            default:
                ControlsProviderLifecycleManager controlsProviderLifecycleManager3 = this.this$0;
                Log.d(controlsProviderLifecycleManager3.TAG, "Timeout waiting onLoadSuggested for " + controlsProviderLifecycleManager3.componentName);
                this.$subscriber.onError(this.this$0.token, "Timeout waiting onLoadSuggested");
                ControlsProviderLifecycleManager controlsProviderLifecycleManager4 = this.this$0;
                ExecutorImpl.ExecutionToken executionToken2 = controlsProviderLifecycleManager4.onLoadCanceller;
                if (executionToken2 != null) {
                    executionToken2.run();
                }
                controlsProviderLifecycleManager4.onLoadCanceller = null;
                ((ExecutorImpl) controlsProviderLifecycleManager4.executor).execute(new ControlsProviderLifecycleManager$bindService$1(controlsProviderLifecycleManager4, false, false));
                break;
        }
    }
}
