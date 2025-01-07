package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TextInclusionStrategy {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;
        public static final TextInclusionStrategy$Companion$$ExternalSyntheticLambda0 AnyOverlap;
        public static final TextInclusionStrategy$Companion$$ExternalSyntheticLambda0 ContainsCenter;

        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0] */
        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0] */
        static {
            final int i = 0;
            AnyOverlap = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.ui.text.TextInclusionStrategy
                public final boolean isIncluded(Rect rect, Rect rect2) {
                    switch (i) {
                        case 0:
                            return rect.overlaps(rect2);
                        default:
                            return rect2.m319containsk4lQ0M(rect.m320getCenterF1C5BW0());
                    }
                }
            };
            final int i2 = 1;
            ContainsCenter = new TextInclusionStrategy() { // from class: androidx.compose.ui.text.TextInclusionStrategy$Companion$$ExternalSyntheticLambda0
                @Override // androidx.compose.ui.text.TextInclusionStrategy
                public final boolean isIncluded(Rect rect, Rect rect2) {
                    switch (i2) {
                        case 0:
                            return rect.overlaps(rect2);
                        default:
                            return rect2.m319containsk4lQ0M(rect.m320getCenterF1C5BW0());
                    }
                }
            };
        }
    }

    boolean isIncluded(Rect rect, Rect rect2);
}
