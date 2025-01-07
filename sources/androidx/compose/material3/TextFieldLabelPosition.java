package androidx.compose.material3;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TextFieldLabelPosition {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Default extends TextFieldLabelPosition {
        public final BiasAlignment.Horizontal expandedAlignment;
        public final BiasAlignment.Horizontal minimizedAlignment;

        public Default() {
            BiasAlignment.Horizontal horizontal = Alignment.Companion.Start;
            this.minimizedAlignment = horizontal;
            this.expandedAlignment = horizontal;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Default)) {
                return false;
            }
            Default r5 = (Default) obj;
            r5.getClass();
            return Intrinsics.areEqual(this.minimizedAlignment, r5.minimizedAlignment) && Intrinsics.areEqual(this.expandedAlignment, r5.expandedAlignment);
        }

        public final int hashCode() {
            return Float.hashCode(this.expandedAlignment.bias) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Boolean.hashCode(false) * 31, this.minimizedAlignment.bias, 31);
        }

        public final String toString() {
            return "Default(alwaysMinimize=false, minimizedAlignment=" + this.minimizedAlignment + ", expandedAlignment=" + this.expandedAlignment + ')';
        }
    }
}
