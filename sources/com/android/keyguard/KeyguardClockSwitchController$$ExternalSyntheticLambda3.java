package com.android.keyguard;

import android.database.ContentObserver;
import com.android.keyguard.KeyguardClockSwitchController;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardClockSwitchController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardClockSwitchController f$0;

    public /* synthetic */ KeyguardClockSwitchController$$ExternalSyntheticLambda3(KeyguardClockSwitchController keyguardClockSwitchController, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardClockSwitchController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardClockSwitchController keyguardClockSwitchController = this.f$0;
        switch (i) {
            case 0:
                keyguardClockSwitchController.mStatusArea.setVisibility(keyguardClockSwitchController.mIsActiveDreamLockscreenHosted ? 4 : 0);
                break;
            case 1:
                KeyguardClockSwitchController.AnonymousClass1 anonymousClass1 = keyguardClockSwitchController.mDoubleLineClockObserver;
                SecureSettings secureSettings = keyguardClockSwitchController.mSecureSettings;
                secureSettings.registerContentObserverForUserSync("lockscreen_use_double_line_clock", false, (ContentObserver) anonymousClass1, -1);
                secureSettings.registerContentObserverForUserSync("lockscreen_weather_enabled", false, (ContentObserver) keyguardClockSwitchController.mShowWeatherObserver, -1);
                break;
            default:
                KeyguardClockSwitchController.AnonymousClass1 anonymousClass12 = keyguardClockSwitchController.mDoubleLineClockObserver;
                SecureSettings secureSettings2 = keyguardClockSwitchController.mSecureSettings;
                secureSettings2.unregisterContentObserverSync(anonymousClass12);
                secureSettings2.unregisterContentObserverSync(keyguardClockSwitchController.mShowWeatherObserver);
                break;
        }
    }
}
