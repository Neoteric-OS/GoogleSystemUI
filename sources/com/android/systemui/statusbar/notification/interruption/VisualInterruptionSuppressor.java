package com.android.systemui.statusbar.notification.interruption;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface VisualInterruptionSuppressor {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EventLogData {
        public final String description;
        public final String number;

        public EventLogData(String str, String str2) {
            this.number = str;
            this.description = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventLogData)) {
                return false;
            }
            EventLogData eventLogData = (EventLogData) obj;
            return Intrinsics.areEqual(this.number, eventLogData.number) && Intrinsics.areEqual(this.description, eventLogData.description);
        }

        public final int hashCode() {
            return this.description.hashCode() + (this.number.hashCode() * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("EventLogData(number=");
            sb.append(this.number);
            sb.append(", description=");
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.description, ")");
        }
    }

    default void start() {
    }
}
