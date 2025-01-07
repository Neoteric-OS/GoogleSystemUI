package androidx.compose.animation.core;

import java.util.Map;
import kotlin.Pair;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class VisibilityThresholdsKt {
    public static final Map VisibilityThresholdMap;

    static {
        Float valueOf = Float.valueOf(0.5f);
        TwoWayConverter twoWayConverter = VectorConvertersKt.IntToVector;
        Float valueOf2 = Float.valueOf(1.0f);
        VisibilityThresholdMap = MapsKt.mapOf(new Pair(twoWayConverter, valueOf2), new Pair(VectorConvertersKt.IntSizeToVector, valueOf2), new Pair(VectorConvertersKt.IntOffsetToVector, valueOf2), new Pair(VectorConvertersKt.FloatToVector, Float.valueOf(0.01f)), new Pair(VectorConvertersKt.RectToVector, valueOf), new Pair(VectorConvertersKt.SizeToVector, valueOf), new Pair(VectorConvertersKt.OffsetToVector, valueOf), new Pair(VectorConvertersKt.DpToVector, Float.valueOf(0.1f)), new Pair(VectorConvertersKt.DpOffsetToVector, Float.valueOf(0.1f)));
    }

    public static final long getVisibilityThreshold() {
        long j = 1;
        return (j & 4294967295L) | (j << 32);
    }

    public static final long getVisibilityThreshold$1() {
        long j = 1;
        return (j & 4294967295L) | (j << 32);
    }
}
