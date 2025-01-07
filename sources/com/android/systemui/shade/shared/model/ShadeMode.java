package com.android.systemui.shade.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShadeMode {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Dual implements ShadeMode {
        public static final Dual INSTANCE = new Dual();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Dual);
        }

        public final int hashCode() {
            return 1185394120;
        }

        public final String toString() {
            return "Dual";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Single implements ShadeMode {
        public static final Single INSTANCE = new Single();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Single);
        }

        public final int hashCode() {
            return 1416156820;
        }

        public final String toString() {
            return "Single";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Split implements ShadeMode {
        public static final Split INSTANCE = new Split();

        public final boolean equals(Object obj) {
            return this == obj || (obj instanceof Split);
        }

        public final int hashCode() {
            return -1893773490;
        }

        public final String toString() {
            return "Split";
        }
    }
}
