package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CrossAxisAlignment {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class HorizontalCrossAxisAlignment extends CrossAxisAlignment {
        public final BiasAlignment.Horizontal horizontal;

        public HorizontalCrossAxisAlignment(BiasAlignment.Horizontal horizontal) {
            this.horizontal = horizontal;
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public final int align$foundation_layout_release(int i, LayoutDirection layoutDirection) {
            return this.horizontal.align(0, i, layoutDirection);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof HorizontalCrossAxisAlignment) && Intrinsics.areEqual(this.horizontal, ((HorizontalCrossAxisAlignment) obj).horizontal);
        }

        public final int hashCode() {
            return Float.hashCode(this.horizontal.bias);
        }

        public final String toString() {
            return "HorizontalCrossAxisAlignment(horizontal=" + this.horizontal + ')';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class VerticalCrossAxisAlignment extends CrossAxisAlignment {
        public final Alignment.Vertical vertical;

        public VerticalCrossAxisAlignment(Alignment.Vertical vertical) {
            this.vertical = vertical;
        }

        @Override // androidx.compose.foundation.layout.CrossAxisAlignment
        public final int align$foundation_layout_release(int i, LayoutDirection layoutDirection) {
            return ((BiasAlignment.Vertical) this.vertical).align(0, i);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof VerticalCrossAxisAlignment) && Intrinsics.areEqual(this.vertical, ((VerticalCrossAxisAlignment) obj).vertical);
        }

        public final int hashCode() {
            return this.vertical.hashCode();
        }

        public final String toString() {
            return "VerticalCrossAxisAlignment(vertical=" + this.vertical + ')';
        }
    }

    public abstract int align$foundation_layout_release(int i, LayoutDirection layoutDirection);
}
