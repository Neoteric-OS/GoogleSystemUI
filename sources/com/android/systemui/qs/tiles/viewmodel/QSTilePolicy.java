package com.android.systemui.qs.tiles.viewmodel;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSTilePolicy {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoRestrictions implements QSTilePolicy {
        public static final NoRestrictions INSTANCE = new NoRestrictions();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof NoRestrictions);
        }

        public final int hashCode() {
            return 1544781172;
        }

        public final String toString() {
            return "NoRestrictions";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Restricted implements QSTilePolicy {
        public final List userRestrictions;

        public Restricted(List list) {
            this.userRestrictions = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Restricted) && Intrinsics.areEqual(this.userRestrictions, ((Restricted) obj).userRestrictions);
        }

        public final int hashCode() {
            return this.userRestrictions.hashCode();
        }

        public final String toString() {
            return "Restricted(userRestrictions=" + this.userRestrictions + ")";
        }
    }
}
