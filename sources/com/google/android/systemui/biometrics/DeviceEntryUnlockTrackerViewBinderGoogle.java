package com.google.android.systemui.biometrics;

import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.google.android.systemui.biometrics.domain.DeviceEntryUnlockTrackerInteractorGoogle;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceEntryUnlockTrackerViewBinderGoogle {
    public final DeviceEntryUnlockTrackerInteractorGoogle interactor;

    public DeviceEntryUnlockTrackerViewBinderGoogle(DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle) {
        this.interactor = deviceEntryUnlockTrackerInteractorGoogle;
    }

    public final void bind(KeyguardRootView keyguardRootView) {
        RepeatWhenAttachedKt.repeatWhenAttached(keyguardRootView, EmptyCoroutineContext.INSTANCE, new DeviceEntryUnlockTrackerViewBinderGoogle$bind$1(this, null));
    }
}
