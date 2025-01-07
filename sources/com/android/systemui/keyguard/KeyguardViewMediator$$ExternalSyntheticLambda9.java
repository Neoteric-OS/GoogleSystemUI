package com.android.systemui.keyguard;

import android.media.SoundPool;
import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.keyguard.KeyguardViewMediator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardViewMediator$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ KeyguardViewMediator$$ExternalSyntheticLambda9(int i, int i2, Object obj) {
        this.$r8$classId = i2;
        this.f$0 = obj;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                KeyguardViewMediator keyguardViewMediator = (KeyguardViewMediator) this.f$0;
                int i = this.f$1;
                if (keyguardViewMediator.mAudioManager.isStreamMute(keyguardViewMediator.mUiSoundsStreamType)) {
                    return;
                }
                SoundPool soundPool = keyguardViewMediator.mLockSounds;
                float f = keyguardViewMediator.mLockSoundVolume;
                int play = soundPool.play(i, f, f, 1, 0, 1.0f);
                synchronized (keyguardViewMediator) {
                    keyguardViewMediator.mLockSoundStreamId = play;
                }
                return;
            case 1:
                KeyguardViewMediator keyguardViewMediator2 = (KeyguardViewMediator) this.f$0;
                int i2 = this.f$1;
                if (keyguardViewMediator2.mLockPatternUtils.isSecure(i2)) {
                    keyguardViewMediator2.mLockPatternUtils.getDevicePolicyManager().reportKeyguardDismissed(i2);
                    return;
                }
                return;
            default:
                KeyguardViewMediator.AnonymousClass14 anonymousClass14 = (KeyguardViewMediator.AnonymousClass14) this.f$0;
                int i3 = this.f$1;
                anonymousClass14.getClass();
                try {
                    anonymousClass14.this$0.mActivityTaskManagerService.keyguardGoingAway(i3);
                    return;
                } catch (RemoteException e) {
                    Log.e("KeyguardViewMediator", "Error while calling WindowManager", e);
                    return;
                }
        }
    }
}
