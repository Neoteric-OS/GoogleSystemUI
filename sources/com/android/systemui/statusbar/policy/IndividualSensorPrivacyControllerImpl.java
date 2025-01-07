package com.android.systemui.statusbar.policy;

import android.hardware.SensorPrivacyManager;
import android.util.SparseBooleanArray;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.IndividualSensorPrivacyController;
import com.android.systemui.util.ListenerSet;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IndividualSensorPrivacyControllerImpl implements IndividualSensorPrivacyController {
    public static final int[] SENSORS = {2, 1};
    public final SensorPrivacyManager mSensorPrivacyManager;
    public final UserTracker mUserTracker;
    public final SparseBooleanArray mSoftwareToggleState = new SparseBooleanArray();
    public final SparseBooleanArray mHardwareToggleState = new SparseBooleanArray();
    public final ListenerSet mCallbacks = new ListenerSet();

    public IndividualSensorPrivacyControllerImpl(SensorPrivacyManager sensorPrivacyManager, UserTracker userTracker) {
        this.mSensorPrivacyManager = sensorPrivacyManager;
        this.mUserTracker = userTracker;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.mCallbacks.addIfAbsent((IndividualSensorPrivacyController.Callback) obj);
    }

    public final boolean isSensorBlocked(int i) {
        return this.mSoftwareToggleState.get(i, false) || this.mHardwareToggleState.get(i, false);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mCallbacks.remove((IndividualSensorPrivacyController.Callback) obj);
    }

    public final void setSensorBlocked(int i, int i2, boolean z) {
        this.mSensorPrivacyManager.setSensorPrivacyForProfileGroup(i, i2, z, ((UserTrackerImpl) this.mUserTracker).getUserId());
    }
}
