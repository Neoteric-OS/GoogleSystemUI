package com.android.systemui.keyguard;

import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import com.android.systemui.util.time.SystemClockImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardViewMediator$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardViewMediator f$0;

    public /* synthetic */ KeyguardViewMediator$$ExternalSyntheticLambda1(KeyguardViewMediator keyguardViewMediator, int i) {
        this.$r8$classId = i;
        this.f$0 = keyguardViewMediator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        KeyguardViewMediator keyguardViewMediator = this.f$0;
        switch (i) {
            case 0:
                keyguardViewMediator.getClass();
                Log.e("KeyguardViewMediator", "mHideAnimationFinishedRunnable#run");
                keyguardViewMediator.mHideAnimationRunning = false;
                keyguardViewMediator.tryKeyguardDone();
                break;
            default:
                PowerManager powerManager = keyguardViewMediator.mPM;
                ((SystemClockImpl) keyguardViewMediator.mSystemClock).getClass();
                powerManager.userActivity(SystemClock.uptimeMillis(), false);
                break;
        }
    }
}
