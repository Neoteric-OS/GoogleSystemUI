package com.android.systemui.smartspace.preconditions;

import android.app.smartspace.SmartspaceSession;
import android.util.Log;
import com.android.systemui.communal.smartspace.CommunalSmartspaceController;
import com.android.systemui.communal.smartspace.CommunalSmartspaceController$preconditionListener$1;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.util.concurrency.ExecutionImpl;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockscreenPrecondition {
    public final DeviceProvisionedController deviceProvisionedController;
    public final LockscreenPrecondition$deviceProvisionedListener$1 deviceProvisionedListener;
    public boolean deviceReady;
    public final ExecutionImpl execution;
    public final Set listeners = new LinkedHashSet();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.systemui.smartspace.preconditions.LockscreenPrecondition$deviceProvisionedListener$1, java.lang.Object] */
    public LockscreenPrecondition(DeviceProvisionedController deviceProvisionedController, ExecutionImpl executionImpl) {
        this.deviceProvisionedController = deviceProvisionedController;
        this.execution = executionImpl;
        ?? r2 = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.smartspace.preconditions.LockscreenPrecondition$deviceProvisionedListener$1
            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onDeviceProvisionedChanged() {
                LockscreenPrecondition.this.updateDeviceReadiness();
            }

            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSetupChanged() {
                LockscreenPrecondition.this.updateDeviceReadiness();
            }
        };
        this.deviceProvisionedListener = r2;
        ((DeviceProvisionedControllerImpl) deviceProvisionedController).addCallback(r2);
        updateDeviceReadiness();
    }

    public final void updateDeviceReadiness() {
        if (this.deviceReady) {
            return;
        }
        boolean z = ((DeviceProvisionedControllerImpl) this.deviceProvisionedController).deviceProvisioned.get() && ((DeviceProvisionedControllerImpl) this.deviceProvisionedController).isCurrentUserSetup();
        this.deviceReady = z;
        if (z) {
            ((DeviceProvisionedControllerImpl) this.deviceProvisionedController).removeCallback(this.deviceProvisionedListener);
            synchronized (this.listeners) {
                Iterator it = this.listeners.iterator();
                while (it.hasNext()) {
                    CommunalSmartspaceController communalSmartspaceController = ((CommunalSmartspaceController$preconditionListener$1) it.next()).this$0;
                    if (communalSmartspaceController.session != null || communalSmartspaceController.listeners.isEmpty()) {
                        SmartspaceSession smartspaceSession = communalSmartspaceController.session;
                        if (smartspaceSession != null) {
                            smartspaceSession.requestSmartspaceUpdate();
                        }
                    } else {
                        Log.d("CommunalSmartspaceCtrlr", "Precondition criteria changed. Attempting to connect session.");
                        communalSmartspaceController.connectSession();
                    }
                }
            }
        }
    }
}
