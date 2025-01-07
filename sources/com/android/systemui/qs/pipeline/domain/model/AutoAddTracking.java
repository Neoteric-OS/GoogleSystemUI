package com.android.systemui.qs.pipeline.domain.model;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface AutoAddTracking {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Always implements AutoAddTracking {
        public static final Always INSTANCE = new Always();

        public final String toString() {
            return "Always";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Disabled implements AutoAddTracking {
        public static final Disabled INSTANCE = new Disabled();

        public final String toString() {
            return "Disabled";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IfNotAdded implements AutoAddTracking {
        public final TileSpec spec;

        public IfNotAdded(TileSpec tileSpec) {
            this.spec = tileSpec;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof IfNotAdded) && Intrinsics.areEqual(this.spec, ((IfNotAdded) obj).spec);
        }

        public final int hashCode() {
            return this.spec.hashCode();
        }

        public final String toString() {
            return "IfNotAdded(spec=" + this.spec + ")";
        }
    }
}
