package com.android.systemui.power.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WakefulnessModel {
    public final WakefulnessState internalWakefulnessState;
    public final WakeSleepReason lastSleepReason;
    public final WakeSleepReason lastWakeReason;
    public final boolean powerButtonLaunchGestureTriggered;

    public WakefulnessModel(WakefulnessState wakefulnessState, WakeSleepReason wakeSleepReason, WakeSleepReason wakeSleepReason2, boolean z) {
        this.internalWakefulnessState = wakefulnessState;
        this.lastWakeReason = wakeSleepReason;
        this.lastSleepReason = wakeSleepReason2;
        this.powerButtonLaunchGestureTriggered = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WakefulnessModel)) {
            return false;
        }
        WakefulnessModel wakefulnessModel = (WakefulnessModel) obj;
        return this.internalWakefulnessState == wakefulnessModel.internalWakefulnessState && this.lastWakeReason == wakefulnessModel.lastWakeReason && this.lastSleepReason == wakefulnessModel.lastSleepReason && this.powerButtonLaunchGestureTriggered == wakefulnessModel.powerButtonLaunchGestureTriggered;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.powerButtonLaunchGestureTriggered) + ((this.lastSleepReason.hashCode() + ((this.lastWakeReason.hashCode() + (this.internalWakefulnessState.hashCode() * 31)) * 31)) * 31);
    }

    public final boolean isAsleep() {
        return !isAwake();
    }

    public final boolean isAwake() {
        WakefulnessState wakefulnessState = WakefulnessState.AWAKE;
        WakefulnessState wakefulnessState2 = this.internalWakefulnessState;
        return wakefulnessState2 == wakefulnessState || wakefulnessState2 == WakefulnessState.STARTING_TO_WAKE;
    }

    public final String toString() {
        return "WakefulnessModel(internalWakefulnessState=" + this.internalWakefulnessState + ", lastWakeReason=" + this.lastWakeReason + ", lastSleepReason=" + this.lastSleepReason + ", powerButtonLaunchGestureTriggered=" + this.powerButtonLaunchGestureTriggered + ")";
    }
}
