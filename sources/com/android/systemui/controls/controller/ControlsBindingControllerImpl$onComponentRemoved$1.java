package com.android.systemui.controls.controller;

import android.content.ComponentName;
import com.android.systemui.controls.controller.ControlsBindingControllerImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsBindingControllerImpl$onComponentRemoved$1 implements Runnable {
    public final /* synthetic */ Object $componentName;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ControlsBindingControllerImpl$onComponentRemoved$1(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.this$0 = obj;
        this.$componentName = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ControlsBindingControllerImpl controlsBindingControllerImpl = (ControlsBindingControllerImpl) this.this$0;
                ControlsProviderLifecycleManager controlsProviderLifecycleManager = controlsBindingControllerImpl.currentProvider;
                if (controlsProviderLifecycleManager != null) {
                    if (Intrinsics.areEqual(controlsProviderLifecycleManager.componentName, (ComponentName) this.$componentName)) {
                        controlsBindingControllerImpl.unbind();
                        break;
                    }
                }
                break;
            default:
                ((ControlsBindingControllerImpl.LoadSubscriber) this.this$0).isTerminated.compareAndSet(false, true);
                ((ControlsBindingControllerImpl.CallbackRunnable) this.$componentName).run();
                break;
        }
    }
}
