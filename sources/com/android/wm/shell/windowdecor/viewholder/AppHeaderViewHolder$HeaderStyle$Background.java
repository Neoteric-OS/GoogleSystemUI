package com.android.wm.shell.windowdecor.viewholder;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AppHeaderViewHolder$HeaderStyle$Background {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Opaque extends AppHeaderViewHolder$HeaderStyle$Background {
        public final int color;

        public Opaque(int i) {
            this.color = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Opaque) && this.color == ((Opaque) obj).color;
        }

        public final int hashCode() {
            return Integer.hashCode(this.color);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("Opaque(color="), this.color, ")");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Transparent extends AppHeaderViewHolder$HeaderStyle$Background {
        public static final Transparent INSTANCE = new Transparent();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Transparent);
        }

        public final int hashCode() {
            return -653789275;
        }

        public final String toString() {
            return "Transparent";
        }
    }
}
