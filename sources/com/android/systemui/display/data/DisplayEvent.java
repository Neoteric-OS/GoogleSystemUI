package com.android.systemui.display.data;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DisplayEvent {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Added implements DisplayEvent {
        public final int displayId;

        public Added(int i) {
            this.displayId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Added) && this.displayId == ((Added) obj).displayId;
        }

        @Override // com.android.systemui.display.data.DisplayEvent
        public final int getDisplayId() {
            return this.displayId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.displayId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Added(displayId="), this.displayId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Changed implements DisplayEvent {
        public final int displayId;

        public Changed(int i) {
            this.displayId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Changed) && this.displayId == ((Changed) obj).displayId;
        }

        @Override // com.android.systemui.display.data.DisplayEvent
        public final int getDisplayId() {
            return this.displayId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.displayId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Changed(displayId="), this.displayId, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Removed implements DisplayEvent {
        public final int displayId;

        public Removed(int i) {
            this.displayId = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Removed) && this.displayId == ((Removed) obj).displayId;
        }

        @Override // com.android.systemui.display.data.DisplayEvent
        public final int getDisplayId() {
            return this.displayId;
        }

        public final int hashCode() {
            return Integer.hashCode(this.displayId);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Removed(displayId="), this.displayId, ")");
        }
    }

    int getDisplayId();
}
