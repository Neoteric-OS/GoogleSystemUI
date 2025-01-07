package androidx.window.embedding;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import androidx.window.core.Bounds;
import androidx.window.layout.FoldingFeature$State;
import androidx.window.layout.HardwareFoldingFeature;
import androidx.window.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EmbeddingBounds {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Alignment alignment;
    public final Dimension height;
    public final Dimension width;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Alignment {
        public final int value;
        public static final Alignment ALIGN_LEFT = new Alignment(0);
        public static final Alignment ALIGN_TOP = new Alignment(1);
        public static final Alignment ALIGN_RIGHT = new Alignment(2);
        public static final Alignment ALIGN_BOTTOM = new Alignment(3);

        public Alignment(int i) {
            this.value = i;
            if (i < 0 || i >= 4) {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Alignment) {
                return this.value == ((Alignment) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return this.value;
        }

        public final String toString() {
            int i = this.value;
            if (i == 0) {
                return "left";
            }
            if (i == 1) {
                return "top";
            }
            if (i == 2) {
                return "right";
            }
            if (i == 3) {
                return "bottom";
            }
            return "unknown position:" + i;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static Bounds offset(Bounds bounds, int i, int i2) {
            return new Bounds(bounds.left + i, bounds.top + i2, bounds.right + i, bounds.bottom + i2);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Dimension {
        public static final Ratio DIMENSION_EXPANDED = new Ratio(1.0f);
        public static final EmbeddingBounds$Dimension$Companion$DIMENSION_HINGE$1 DIMENSION_HINGE = new EmbeddingBounds$Dimension$Companion$DIMENSION_HINGE$1("hinge");
        public final String description;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Pixel extends Dimension {
            public final int value;

            public Pixel(int i) {
                super(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "dimension in pixel:"));
                this.value = i;
                if (i < 1) {
                    throw new IllegalArgumentException("Pixel value must be a positive integer.");
                }
            }
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class Ratio extends Dimension {
            public final float value;

            public Ratio(float f) {
                super("dimension in ratio:" + f);
                this.value = f;
                if (f <= 0.0d || f > 1.0d) {
                    throw new IllegalArgumentException("Ratio must be in range (0.0, 1.0]");
                }
            }
        }

        public Dimension(String str) {
            this.description = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Dimension)) {
                return false;
            }
            return Intrinsics.areEqual(this.description, ((Dimension) obj).description);
        }

        public final int hashCode() {
            return this.description.hashCode();
        }

        public final String toString() {
            return this.description;
        }
    }

    static {
        Alignment alignment = Alignment.ALIGN_LEFT;
        Dimension.Ratio ratio = Dimension.DIMENSION_EXPANDED;
    }

    public EmbeddingBounds(Alignment alignment, Dimension dimension, Dimension dimension2) {
        this.alignment = alignment;
        this.width = dimension;
        this.height = dimension2;
    }

    public static HardwareFoldingFeature getOnlyFoldingFeatureOrNull(WindowLayoutInfo windowLayoutInfo) {
        List list = windowLayoutInfo.displayFeatures;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof HardwareFoldingFeature) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() == 1) {
            return (HardwareFoldingFeature) arrayList.get(0);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmbeddingBounds)) {
            return false;
        }
        EmbeddingBounds embeddingBounds = (EmbeddingBounds) obj;
        return Intrinsics.areEqual(this.alignment, embeddingBounds.alignment) && Intrinsics.areEqual(this.width, embeddingBounds.width) && Intrinsics.areEqual(this.height, embeddingBounds.height);
    }

    public final int hashCode() {
        return this.height.description.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.width.description, this.alignment.value * 31, 31);
    }

    public final boolean shouldUseFallbackDimensionForHeight$window_release(WindowLayoutInfo windowLayoutInfo) {
        if (!Intrinsics.areEqual(this.height, Dimension.DIMENSION_HINGE)) {
            return false;
        }
        HardwareFoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        return !(onlyFoldingFeatureOrNull == null ? false : onlyFoldingFeatureOrNull.getOrientation().equals(FoldingFeature$State.HORIZONTAL)) || CollectionsKt__CollectionsKt.listOf(Alignment.ALIGN_LEFT, Alignment.ALIGN_RIGHT).contains(this.alignment);
    }

    public final boolean shouldUseFallbackDimensionForWidth$window_release(WindowLayoutInfo windowLayoutInfo) {
        if (!Intrinsics.areEqual(this.width, Dimension.DIMENSION_HINGE)) {
            return false;
        }
        HardwareFoldingFeature onlyFoldingFeatureOrNull = getOnlyFoldingFeatureOrNull(windowLayoutInfo);
        return !(onlyFoldingFeatureOrNull == null ? false : onlyFoldingFeatureOrNull.getOrientation().equals(FoldingFeature$State.VERTICAL)) || CollectionsKt__CollectionsKt.listOf(Alignment.ALIGN_TOP, Alignment.ALIGN_BOTTOM).contains(this.alignment);
    }

    public final String toString() {
        return "Bounds:{alignment=" + this.alignment + ", width=" + this.width + ", height=" + this.height + '}';
    }
}
