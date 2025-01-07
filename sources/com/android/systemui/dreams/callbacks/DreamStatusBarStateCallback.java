package com.android.systemui.dreams.callbacks;

import android.util.Log;
import com.android.settingslib.mobile.MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0;
import com.android.systemui.DejankUtils;
import com.android.systemui.shared.condition.Monitor;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamStatusBarStateCallback implements Monitor.Callback {
    public final SysuiStatusBarStateController mStateController;

    public DreamStatusBarStateCallback(SysuiStatusBarStateController sysuiStatusBarStateController) {
        this.mStateController = sysuiStatusBarStateController;
    }

    @Override // com.android.systemui.shared.condition.Monitor.Callback
    public final void onConditionsChanged(boolean z) {
        if (Log.isLoggable("DreamStatusBarCallback", 3)) {
            MobileStatusTracker$MobileTelephonyCallback$$ExternalSyntheticOutline0.m("onConditionChanged:", "DreamStatusBarCallback", z);
        }
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStateController;
        if (Log.isLoggable("SbStateController", 3)) {
            statusBarStateControllerImpl.getClass();
            Log.d("SbStateController", "setIsDreaming:" + z);
        }
        if (statusBarStateControllerImpl.mIsDreaming == z) {
            return;
        }
        statusBarStateControllerImpl.mIsDreaming = z;
        synchronized (statusBarStateControllerImpl.mListeners) {
            try {
                DejankUtils.startDetectingBlockingIpcs("StatusBarStateControllerImpl#setIsDreaming");
                Iterator it = new ArrayList(statusBarStateControllerImpl.mListeners).iterator();
                while (it.hasNext()) {
                    ((SysuiStatusBarStateController.RankedListener) it.next()).mListener.onDreamingChanged(z);
                }
                DejankUtils.stopDetectingBlockingIpcs("StatusBarStateControllerImpl#setIsDreaming");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
