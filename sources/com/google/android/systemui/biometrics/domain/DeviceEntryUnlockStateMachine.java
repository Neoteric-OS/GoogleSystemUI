package com.google.android.systemui.biometrics.domain;

import com.google.android.systemui.biometrics.DeviceEntryUnlockStage;
import kotlin.collections.SetsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceEntryUnlockStateMachine {
    public DeviceEntryUnlockStage current;
    public DeviceEntryUnlockStage previous;

    public DeviceEntryUnlockStateMachine() {
        DeviceEntryUnlockStage deviceEntryUnlockStage = DeviceEntryUnlockStage.UNKNOWN;
        this.current = deviceEntryUnlockStage;
        this.previous = deviceEntryUnlockStage;
    }

    public final boolean tryTransitTo(DeviceEntryUnlockStage deviceEntryUnlockStage) {
        int ordinal = deviceEntryUnlockStage.ordinal();
        if (ordinal == 0) {
            this.previous = this.current;
            this.current = DeviceEntryUnlockStage.CANCELED;
            return true;
        }
        if (ordinal == 1) {
            DeviceEntryUnlockStage deviceEntryUnlockStage2 = this.current;
            if (deviceEntryUnlockStage2 != DeviceEntryUnlockStage.STARTED && deviceEntryUnlockStage2 != DeviceEntryUnlockStage.HAL_ACQUISITION) {
                return false;
            }
            this.previous = deviceEntryUnlockStage2;
            this.current = DeviceEntryUnlockStage.HAL_ACQUISITION;
            return true;
        }
        if (ordinal == 2) {
            DeviceEntryUnlockStage deviceEntryUnlockStage3 = this.current;
            if (deviceEntryUnlockStage3 != DeviceEntryUnlockStage.HAL_ACQUISITION && deviceEntryUnlockStage3 != DeviceEntryUnlockStage.STOPPED) {
                return false;
            }
            this.previous = deviceEntryUnlockStage3;
            this.current = DeviceEntryUnlockStage.HAL_AUTHENTICATED;
            return true;
        }
        if (ordinal == 3) {
            DeviceEntryUnlockStage deviceEntryUnlockStage4 = this.current;
            if (deviceEntryUnlockStage4 != DeviceEntryUnlockStage.HAL_AUTHENTICATED) {
                return false;
            }
            this.previous = deviceEntryUnlockStage4;
            this.current = DeviceEntryUnlockStage.EXIT_KEYGUARD;
            return true;
        }
        if (ordinal == 4) {
            boolean contains = SetsKt.setOf(DeviceEntryUnlockStage.UNKNOWN, DeviceEntryUnlockStage.CANCELED, DeviceEntryUnlockStage.STOPPED, DeviceEntryUnlockStage.UNLOCKED).contains(this.current);
            this.previous = this.current;
            this.current = DeviceEntryUnlockStage.STARTED;
            return contains;
        }
        if (ordinal == 5) {
            DeviceEntryUnlockStage deviceEntryUnlockStage5 = this.current;
            if (deviceEntryUnlockStage5 == DeviceEntryUnlockStage.HAL_AUTHENTICATED) {
                return false;
            }
            this.previous = deviceEntryUnlockStage5;
            this.current = DeviceEntryUnlockStage.STOPPED;
            return true;
        }
        if (ordinal != 7) {
            return false;
        }
        DeviceEntryUnlockStage deviceEntryUnlockStage6 = this.current;
        boolean z = deviceEntryUnlockStage6 == DeviceEntryUnlockStage.EXIT_KEYGUARD;
        this.previous = deviceEntryUnlockStage6;
        this.current = DeviceEntryUnlockStage.UNLOCKED;
        return z;
    }
}
