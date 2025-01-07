package com.android.systemui.accessibility.data.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import java.time.LocalTime;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface NightDisplayChangeEvent {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnActivatedChanged implements NightDisplayChangeEvent {
        public final boolean isActivated;

        public OnActivatedChanged(boolean z) {
            this.isActivated = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnActivatedChanged) && this.isActivated == ((OnActivatedChanged) obj).isActivated;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isActivated);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnActivatedChanged(isActivated="), this.isActivated, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnAutoModeChanged implements NightDisplayChangeEvent {
        public final int autoMode;

        public OnAutoModeChanged(int i) {
            this.autoMode = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnAutoModeChanged) && this.autoMode == ((OnAutoModeChanged) obj).autoMode;
        }

        public final int hashCode() {
            return Integer.hashCode(this.autoMode);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("OnAutoModeChanged(autoMode="), this.autoMode, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnCustomEndTimeChanged implements NightDisplayChangeEvent {
        public final LocalTime endTime;

        public OnCustomEndTimeChanged(LocalTime localTime) {
            this.endTime = localTime;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnCustomEndTimeChanged) && Intrinsics.areEqual(this.endTime, ((OnCustomEndTimeChanged) obj).endTime);
        }

        public final int hashCode() {
            LocalTime localTime = this.endTime;
            if (localTime == null) {
                return 0;
            }
            return localTime.hashCode();
        }

        public final String toString() {
            return "OnCustomEndTimeChanged(endTime=" + this.endTime + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnCustomStartTimeChanged implements NightDisplayChangeEvent {
        public final LocalTime startTime;

        public OnCustomStartTimeChanged(LocalTime localTime) {
            this.startTime = localTime;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnCustomStartTimeChanged) && Intrinsics.areEqual(this.startTime, ((OnCustomStartTimeChanged) obj).startTime);
        }

        public final int hashCode() {
            LocalTime localTime = this.startTime;
            if (localTime == null) {
                return 0;
            }
            return localTime.hashCode();
        }

        public final String toString() {
            return "OnCustomStartTimeChanged(startTime=" + this.startTime + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnForceAutoModeChanged implements NightDisplayChangeEvent {
        public final boolean shouldForceAutoMode;

        public OnForceAutoModeChanged(boolean z) {
            this.shouldForceAutoMode = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnForceAutoModeChanged) && this.shouldForceAutoMode == ((OnForceAutoModeChanged) obj).shouldForceAutoMode;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.shouldForceAutoMode);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnForceAutoModeChanged(shouldForceAutoMode="), this.shouldForceAutoMode, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OnLocationEnabledChanged implements NightDisplayChangeEvent {
        public final boolean locationEnabled;

        public OnLocationEnabledChanged(boolean z) {
            this.locationEnabled = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof OnLocationEnabledChanged) && this.locationEnabled == ((OnLocationEnabledChanged) obj).locationEnabled;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.locationEnabled);
        }

        public final String toString() {
            return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("OnLocationEnabledChanged(locationEnabled="), this.locationEnabled, ")");
        }
    }
}
