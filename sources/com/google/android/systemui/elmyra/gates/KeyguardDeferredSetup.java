package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.android.systemui.elmyra.UserContentObserver;
import com.google.android.systemui.elmyra.actions.Action;
import com.google.android.systemui.elmyra.gates.Gate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KeyguardDeferredSetup extends Gate {
    public boolean mDeferredSetupComplete;
    public final List mExceptions;
    public final KeyguardVisibility mKeyguardGate;
    public final AnonymousClass1 mKeyguardGateListener;
    public final SecureSettings mSecureSettings;
    public final UserContentObserver mSettingsObserver;

    public KeyguardDeferredSetup(Context context, Executor executor, KeyguardVisibility keyguardVisibility, SecureSettings secureSettings, Set set) {
        super(executor);
        Gate.Listener listener = new Gate.Listener() { // from class: com.google.android.systemui.elmyra.gates.KeyguardDeferredSetup.1
            @Override // com.google.android.systemui.elmyra.gates.Gate.Listener
            public final void onGateChanged(Gate gate) {
                KeyguardDeferredSetup.this.notifyListener();
            }
        };
        this.mSecureSettings = secureSettings;
        this.mExceptions = new ArrayList(set);
        this.mKeyguardGate = keyguardVisibility;
        keyguardVisibility.mListener = listener;
        ((SecureSettingsImpl) secureSettings).getClass();
        this.mSettingsObserver = new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_setup_complete"), new Consumer() { // from class: com.google.android.systemui.elmyra.gates.KeyguardDeferredSetup$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                KeyguardDeferredSetup keyguardDeferredSetup = KeyguardDeferredSetup.this;
                boolean z = keyguardDeferredSetup.mSecureSettings.getIntForUser("assist_gesture_setup_complete", 0, -2) != 0;
                if (keyguardDeferredSetup.mDeferredSetupComplete != z) {
                    keyguardDeferredSetup.mDeferredSetupComplete = z;
                    keyguardDeferredSetup.notifyListener();
                }
            }
        }, false);
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        for (int i = 0; i < ((ArrayList) this.mExceptions).size(); i++) {
            if (((Action) ((ArrayList) this.mExceptions).get(i)).isAvailable()) {
                return false;
            }
        }
        return !this.mDeferredSetupComplete && this.mKeyguardGate.isBlocking();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mKeyguardGate.activate();
        this.mDeferredSetupComplete = this.mSecureSettings.getIntForUser("assist_gesture_setup_complete", 0, -2) != 0;
        this.mSettingsObserver.activate();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mKeyguardGate.deactivate();
        this.mSettingsObserver.deactivate();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mDeferredSetupComplete -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mDeferredSetupComplete, "]");
    }
}
