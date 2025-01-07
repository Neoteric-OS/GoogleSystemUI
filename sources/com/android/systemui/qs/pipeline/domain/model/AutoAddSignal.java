package com.android.systemui.qs.pipeline.domain.model;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface AutoAddSignal {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Add implements AutoAddSignal {
        public final int position;
        public final TileSpec spec;

        public Add(int i, TileSpec tileSpec) {
            this.spec = tileSpec;
            this.position = i;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Add)) {
                return false;
            }
            Add add = (Add) obj;
            return Intrinsics.areEqual(this.spec, add.spec) && this.position == add.position;
        }

        public final int hashCode() {
            return Integer.hashCode(this.position) + (this.spec.hashCode() * 31);
        }

        public final String toString() {
            return "Add(spec=" + this.spec + ", position=" + this.position + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Remove implements AutoAddSignal {
        public final TileSpec spec;

        public Remove(TileSpec tileSpec) {
            this.spec = tileSpec;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Remove) && Intrinsics.areEqual(this.spec, ((Remove) obj).spec);
        }

        public final int hashCode() {
            return this.spec.hashCode();
        }

        public final String toString() {
            return "Remove(spec=" + this.spec + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoveTracking implements AutoAddSignal {
        public final TileSpec spec;

        public RemoveTracking(TileSpec tileSpec) {
            this.spec = tileSpec;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof RemoveTracking) && Intrinsics.areEqual(this.spec, ((RemoveTracking) obj).spec);
        }

        public final int hashCode() {
            return this.spec.hashCode();
        }

        public final String toString() {
            return "RemoveTracking(spec=" + this.spec + ")";
        }
    }
}
