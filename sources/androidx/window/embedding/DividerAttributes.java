package androidx.window.embedding;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.activity.BackEventCompat$$ExternalSyntheticOutline0;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.DarkIconDispatcher;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DividerAttributes {
    public static final Companion Companion = null;
    public static final DividerAttributes$Companion$NO_DIVIDER$1 NO_DIVIDER = new DividerAttributes$Companion$NO_DIVIDER$1(-1, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT);
    public final int color;
    public final int widthDp;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$validateColor(int i) {
            if ((i >>> 24) == 255) {
                return;
            }
            throw new IllegalArgumentException(("Divider color must be opaque. Got: " + Integer.toHexString(i)).toString());
        }

        public static final void access$validateWidth(int i) {
            if (i != -1 && i < 0) {
                throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "widthDp must be greater than or equal to 0 or WIDTH_SYSTEM_DEFAULT. Got: ").toString());
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DragRange {
        public static final DividerAttributes$DragRange$Companion$DRAG_RANGE_SYSTEM_DEFAULT$1 DRAG_RANGE_SYSTEM_DEFAULT = new DividerAttributes$DragRange$Companion$DRAG_RANGE_SYSTEM_DEFAULT$1();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class SplitRatioDragRange extends DragRange {
            public final float maxRatio;
            public final float minRatio;

            public SplitRatioDragRange(float f, float f2) {
                this.minRatio = f;
                this.maxRatio = f2;
                if (f <= 0.0d || f >= 1.0d) {
                    throw new IllegalArgumentException("minRatio must be in the interval (0.0, 1.0)");
                }
                if (f2 <= 0.0d || f2 >= 1.0d) {
                    throw new IllegalArgumentException("maxRatio must be in the interval (0.0, 1.0)");
                }
                if (f > f2) {
                    throw new IllegalArgumentException("minRatio must be less than or equal to maxRatio");
                }
            }

            public final boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SplitRatioDragRange)) {
                    return false;
                }
                SplitRatioDragRange splitRatioDragRange = (SplitRatioDragRange) obj;
                return this.minRatio == splitRatioDragRange.minRatio && this.maxRatio == splitRatioDragRange.maxRatio;
            }

            public final int hashCode() {
                return Float.hashCode(this.maxRatio) + (Float.hashCode(this.minRatio) * 31);
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder("SplitRatioDragRange[");
                sb.append(this.minRatio);
                sb.append(", ");
                return AndroidFlingSpline$$ExternalSyntheticOutline0.m(sb, this.maxRatio, ']');
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DraggableDividerAttributes extends DividerAttributes {
        public final DragRange dragRange;
        public final boolean isDraggingToFullscreenAllowed;

        public DraggableDividerAttributes(int i, int i2, DragRange dragRange, boolean z) {
            super(i, i2);
            this.dragRange = dragRange;
            this.isDraggingToFullscreenAllowed = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DraggableDividerAttributes)) {
                return false;
            }
            DraggableDividerAttributes draggableDividerAttributes = (DraggableDividerAttributes) obj;
            if (this.widthDp == draggableDividerAttributes.widthDp) {
                if (this.color == draggableDividerAttributes.color && Intrinsics.areEqual(this.dragRange, draggableDividerAttributes.dragRange) && this.isDraggingToFullscreenAllowed == draggableDividerAttributes.isDraggingToFullscreenAllowed) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.isDraggingToFullscreenAllowed) + ((this.dragRange.hashCode() + (((this.widthDp * 31) + this.color) * 31)) * 31);
        }

        @Override // androidx.window.embedding.DividerAttributes
        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(DraggableDividerAttributes.class.getSimpleName());
            sb.append("{width=");
            sb.append(this.widthDp);
            sb.append(", color=");
            sb.append(this.color);
            sb.append(", primaryContainerDragRange=");
            sb.append(this.dragRange);
            sb.append(", isDraggingToFullscreenAllowed=");
            return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.isDraggingToFullscreenAllowed, '}');
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FixedDividerAttributes extends DividerAttributes {
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FixedDividerAttributes)) {
                return false;
            }
            FixedDividerAttributes fixedDividerAttributes = (FixedDividerAttributes) obj;
            if (this.widthDp == fixedDividerAttributes.widthDp) {
                if (this.color == fixedDividerAttributes.color) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return (this.widthDp * 31) + this.color;
        }
    }

    public DividerAttributes(int i, int i2) {
        this.widthDp = i;
        this.color = i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DividerAttributes{width=");
        sb.append(this.widthDp);
        sb.append(", color=");
        return BackEventCompat$$ExternalSyntheticOutline0.m(sb, this.color, '}');
    }
}
