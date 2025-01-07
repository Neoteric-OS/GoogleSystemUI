package com.google.android.systemui.columbus.legacy.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardVisibility extends Gate {
    public final KeyguardVisibility$keyguardMonitorCallback$1 keyguardMonitorCallback = new KeyguardStateController.Callback() { // from class: com.google.android.systemui.columbus.legacy.gates.KeyguardVisibility$keyguardMonitorCallback$1
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            KeyguardVisibility keyguardVisibility = KeyguardVisibility.this;
            keyguardVisibility.setBlocking(((KeyguardStateControllerImpl) ((KeyguardStateController) keyguardVisibility.keyguardStateController.get())).mShowing);
        }
    };
    public final Lazy keyguardStateController;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.columbus.legacy.gates.KeyguardVisibility$keyguardMonitorCallback$1] */
    public KeyguardVisibility(Lazy lazy) {
        this.keyguardStateController = lazy;
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onActivate() {
        Lazy lazy = this.keyguardStateController;
        ((KeyguardStateControllerImpl) ((KeyguardStateController) lazy.get())).addCallback(this.keyguardMonitorCallback);
        setBlocking(((KeyguardStateControllerImpl) ((KeyguardStateController) lazy.get())).mShowing);
    }

    @Override // com.google.android.systemui.columbus.legacy.gates.Gate
    public final void onDeactivate() {
        ((KeyguardStateControllerImpl) ((KeyguardStateController) this.keyguardStateController.get())).removeCallback(this.keyguardMonitorCallback);
    }
}
