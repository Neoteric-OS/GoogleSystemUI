package com.android.settingslib.volume.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface VolumeControllerEvent {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Dismiss implements VolumeControllerEvent {
        public static final Dismiss INSTANCE = new Dismiss();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Dismiss);
        }

        public final int hashCode() {
            return 524319987;
        }

        public final String toString() {
            return "Dismiss";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayCsdWarning implements VolumeControllerEvent {
        public final int csdWarning;
        public final int displayDurationMs;

        public DisplayCsdWarning(int i, int i2) {
            this.csdWarning = i;
            this.displayDurationMs = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DisplayCsdWarning)) {
                return false;
            }
            DisplayCsdWarning displayCsdWarning = (DisplayCsdWarning) obj;
            return this.csdWarning == displayCsdWarning.csdWarning && this.displayDurationMs == displayCsdWarning.displayDurationMs;
        }

        public final int hashCode() {
            return Integer.hashCode(this.displayDurationMs) + (Integer.hashCode(this.csdWarning) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("DisplayCsdWarning(csdWarning=");
            sb.append(this.csdWarning);
            sb.append(", displayDurationMs=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.displayDurationMs, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplaySafeVolumeWarning implements VolumeControllerEvent {
        public final int flags;

        public DisplaySafeVolumeWarning(int i) {
            this.flags = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof DisplaySafeVolumeWarning) && this.flags == ((DisplaySafeVolumeWarning) obj).flags;
        }

        public final int hashCode() {
            return Integer.hashCode(this.flags);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("DisplaySafeVolumeWarning(flags="), this.flags, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MasterMuteChanged implements VolumeControllerEvent {
        public final int flags;

        public MasterMuteChanged(int i) {
            this.flags = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MasterMuteChanged) && this.flags == ((MasterMuteChanged) obj).flags;
        }

        public final int hashCode() {
            return Integer.hashCode(this.flags);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("MasterMuteChanged(flags="), this.flags, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SetA11yMode implements VolumeControllerEvent {
        public final int mode;

        public SetA11yMode(int i) {
            this.mode = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SetA11yMode) && this.mode == ((SetA11yMode) obj).mode;
        }

        public final int hashCode() {
            return Integer.hashCode(this.mode);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("SetA11yMode(mode="), this.mode, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SetLayoutDirection implements VolumeControllerEvent {
        public final int layoutDirection;

        public SetLayoutDirection(int i) {
            this.layoutDirection = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof SetLayoutDirection) && this.layoutDirection == ((SetLayoutDirection) obj).layoutDirection;
        }

        public final int hashCode() {
            return Integer.hashCode(this.layoutDirection);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("SetLayoutDirection(layoutDirection="), this.layoutDirection, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VolumeChanged implements VolumeControllerEvent {
        public final int flags;
        public final int streamType;

        public VolumeChanged(int i, int i2) {
            this.streamType = i;
            this.flags = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VolumeChanged)) {
                return false;
            }
            VolumeChanged volumeChanged = (VolumeChanged) obj;
            return this.streamType == volumeChanged.streamType && this.flags == volumeChanged.flags;
        }

        public final int hashCode() {
            return Integer.hashCode(this.flags) + (Integer.hashCode(this.streamType) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("VolumeChanged(streamType=");
            sb.append(this.streamType);
            sb.append(", flags=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.flags, ")");
        }
    }
}
