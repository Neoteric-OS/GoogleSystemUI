package com.google.android.systemui.elmyra.gates;

import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardVisibility extends Gate {
    public final AnonymousClass1 mKeyguardMonitorCallback;
    public final KeyguardStateController mKeyguardStateController;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.elmyra.gates.KeyguardVisibility$1] */
    public KeyguardVisibility(Executor executor, KeyguardStateController keyguardStateController) {
        super(executor);
        this.mKeyguardMonitorCallback = new KeyguardStateController.Callback() { // from class: com.google.android.systemui.elmyra.gates.KeyguardVisibility.1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                KeyguardVisibility.this.notifyListener();
            }
        };
        this.mKeyguardStateController = keyguardStateController;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        return ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing;
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardMonitorCallback);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).removeCallback(this.mKeyguardMonitorCallback);
    }
}
