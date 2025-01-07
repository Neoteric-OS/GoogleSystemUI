package com.google.android.systemui.elmyra.gates;

import android.content.Context;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SecureSettingsImpl;
import com.google.android.systemui.elmyra.UserContentObserver;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WakeMode extends PowerState {
    public final SecureSettings mSettings;
    public final UserContentObserver mSettingsObserver;
    public boolean mWakeSettingEnabled;

    public WakeMode(Context context, Executor executor, PowerManager powerManager, KeyguardUpdateMonitor keyguardUpdateMonitor, SecureSettings secureSettings) {
        super(executor, powerManager, keyguardUpdateMonitor);
        this.mSettings = secureSettings;
        ((SecureSettingsImpl) secureSettings).getClass();
        this.mSettingsObserver = new UserContentObserver(context, Settings.Secure.getUriFor("assist_gesture_wake_enabled"), new Consumer() { // from class: com.google.android.systemui.elmyra.gates.WakeMode$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                WakeMode wakeMode = WakeMode.this;
                boolean z = wakeMode.mSettings.getIntForUser("assist_gesture_wake_enabled", 1, -2) != 0;
                if (z != wakeMode.mWakeSettingEnabled) {
                    wakeMode.mWakeSettingEnabled = z;
                    wakeMode.notifyListener();
                }
            }
        }, false);
    }

    @Override // com.google.android.systemui.elmyra.gates.PowerState, com.google.android.systemui.elmyra.gates.Gate
    public final boolean isBlocked() {
        if (this.mWakeSettingEnabled) {
            return false;
        }
        return super.isBlocked();
    }

    @Override // com.google.android.systemui.elmyra.gates.PowerState, com.google.android.systemui.elmyra.gates.Gate
    public final void onActivate() {
        this.mWakeSettingEnabled = this.mSettings.getIntForUser("assist_gesture_wake_enabled", 1, -2) != 0;
        this.mSettingsObserver.activate();
    }

    @Override // com.google.android.systemui.elmyra.gates.PowerState, com.google.android.systemui.elmyra.gates.Gate
    public final void onDeactivate() {
        this.mSettingsObserver.deactivate();
    }

    @Override // com.google.android.systemui.elmyra.gates.Gate
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mWakeSettingEnabled -> ");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.mWakeSettingEnabled, "]");
    }
}
