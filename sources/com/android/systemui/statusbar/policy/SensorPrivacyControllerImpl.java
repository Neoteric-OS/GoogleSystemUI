package com.android.systemui.statusbar.policy;

import android.hardware.SensorPrivacyManager;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$6$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorPrivacyControllerImpl implements SensorPrivacyManager.OnAllSensorPrivacyChangedListener, CallbackController {
    public final List mListeners = new ArrayList(1);
    public final Object mLock = new Object();
    public boolean mSensorPrivacyEnabled;
    public final SensorPrivacyManager mSensorPrivacyManager;

    public SensorPrivacyControllerImpl(SensorPrivacyManager sensorPrivacyManager) {
        this.mSensorPrivacyManager = sensorPrivacyManager;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        PhoneStatusBarPolicy.AnonymousClass6 anonymousClass6 = (PhoneStatusBarPolicy.AnonymousClass6) obj;
        synchronized (this.mLock) {
            this.mListeners.add(anonymousClass6);
            PhoneStatusBarPolicy.this.mHandler.post(new PhoneStatusBarPolicy$6$$ExternalSyntheticLambda0(anonymousClass6, this.mSensorPrivacyEnabled));
        }
    }

    public final void onAllSensorPrivacyChanged(boolean z) {
        synchronized (this.mLock) {
            try {
                this.mSensorPrivacyEnabled = z;
                for (PhoneStatusBarPolicy.AnonymousClass6 anonymousClass6 : this.mListeners) {
                    PhoneStatusBarPolicy.this.mHandler.post(new PhoneStatusBarPolicy$6$$ExternalSyntheticLambda0(anonymousClass6, this.mSensorPrivacyEnabled));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        PhoneStatusBarPolicy.AnonymousClass6 anonymousClass6 = (PhoneStatusBarPolicy.AnonymousClass6) obj;
        synchronized (this.mLock) {
            this.mListeners.remove(anonymousClass6);
        }
    }
}
