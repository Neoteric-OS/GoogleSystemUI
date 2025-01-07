package com.google.android.systemui.elmyra.gates;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.google.android.systemui.elmyra.actions.SettingsAction;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SetupWizard extends Gate {
    public final DeviceProvisionedController mProvisionedController;
    public final AnonymousClass1 mProvisionedListener;
    public final SettingsAction mSettingsAction;
    public boolean mSetupComplete;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.elmyra.gates.SetupWizard$1] */
    public SetupWizard(Executor executor, DeviceProvisionedController deviceProvisionedController, SettingsAction settingsAction) {
        super(executor);
        this.mProvisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.google.android.systemui.elmyra.gates.SetupWizard.1
            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onDeviceProvisionedChanged() {
                SetupWizard setupWizard = SetupWizard.this;
                DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) setupWizard.mProvisionedController;
                boolean z = deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup();
                if (z != setupWizard.mSetupComplete) {
                    setupWizard.mSetupComplete = z;
                    setupWizard.notifyListener();
                }
            }

            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSetupChanged() {
                SetupWizard setupWizard = SetupWizard.this;
                DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) setupWizard.mProvisionedController;
                boolean z = deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup();
                if (z != setupWizard.mSetupComplete) {
                    setupWizard.mSetupComplete = z;
                    setupWizard.notifyListener();
                }
            }
        };
        this.mSettingsAction = settingsAction;
        this.mProvisionedController = deviceProvisionedController;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        if (this.mSettingsAction.isAvailable()) {
            return false;
        }
        return !this.mSetupComplete;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        DeviceProvisionedController deviceProvisionedController = this.mProvisionedController;
        DeviceProvisionedControllerImpl deviceProvisionedControllerImpl = (DeviceProvisionedControllerImpl) deviceProvisionedController;
        this.mSetupComplete = deviceProvisionedControllerImpl.deviceProvisioned.get() && deviceProvisionedControllerImpl.isCurrentUserSetup();
        ((DeviceProvisionedControllerImpl) deviceProvisionedController).addCallback(this.mProvisionedListener);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        ((DeviceProvisionedControllerImpl) this.mProvisionedController).removeCallback(this.mProvisionedListener);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [isDeviceProvisioned -> ");
        DeviceProvisionedController deviceProvisionedController = this.mProvisionedController;
        sb.append(((DeviceProvisionedControllerImpl) deviceProvisionedController).deviceProvisioned.get());
        sb.append("; isCurrentUserSetup -> ");
        sb.append(((DeviceProvisionedControllerImpl) deviceProvisionedController).isCurrentUserSetup());
        sb.append("]");
        return sb.toString();
    }
}
