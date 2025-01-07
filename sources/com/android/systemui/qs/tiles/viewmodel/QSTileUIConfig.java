package com.android.systemui.qs.tiles.viewmodel;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSTileUIConfig {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Empty implements QSTileUIConfig {
        public static final Empty INSTANCE = new Empty();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Empty);
        }

        @Override // com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig
        public final int getIconRes() {
            return 0;
        }

        @Override // com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig
        public final int getLabelRes() {
            return 0;
        }

        public final int hashCode() {
            return -1817589883;
        }

        public final String toString() {
            return "Empty";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Resource implements QSTileUIConfig {
        public final int iconRes;
        public final int labelRes;

        public Resource(int i, int i2) {
            this.iconRes = i;
            this.labelRes = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Resource)) {
                return false;
            }
            Resource resource = (Resource) obj;
            return this.iconRes == resource.iconRes && this.labelRes == resource.labelRes;
        }

        @Override // com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig
        public final int getIconRes() {
            return this.iconRes;
        }

        @Override // com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig
        public final int getLabelRes() {
            return this.labelRes;
        }

        public final int hashCode() {
            return Integer.hashCode(this.labelRes) + (Integer.hashCode(this.iconRes) * 31);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Resource(iconRes=");
            sb.append(this.iconRes);
            sb.append(", labelRes=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.labelRes, ")");
        }
    }

    int getIconRes();

    int getLabelRes();
}
