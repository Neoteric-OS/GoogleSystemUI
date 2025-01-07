package com.android.systemui.statusbar.chips.casttootherdevice.domain.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface MediaRouterCastModel {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Casting implements MediaRouterCastModel {
        public final String deviceName;

        public Casting(String str) {
            this.deviceName = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Casting) && Intrinsics.areEqual(this.deviceName, ((Casting) obj).deviceName);
        }

        public final int hashCode() {
            String str = this.deviceName;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public final String toString() {
            return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("Casting(deviceName="), this.deviceName, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DoingNothing implements MediaRouterCastModel {
        public static final DoingNothing INSTANCE = new DoingNothing();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof DoingNothing);
        }

        public final int hashCode() {
            return 1316565069;
        }

        public final String toString() {
            return "DoingNothing";
        }
    }
}
